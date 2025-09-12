package Hospital.presentation.dashboard;

import Hospital.logic.Medicamento;
import Hospital.logic.recetas.Prescripcion;
import Hospital.logic.recetas.Receta;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class View implements PropertyChangeListener {
    private JLabel Desde;
    private JPanel fechaParaDesde;
    private JLabel Hasta;
    private JComboBox listaMedicamentos;
    private JButton aceptarButton;
    private JTable table1;
    private JPanel panelDatos;
    private JPanel fechaParaHasta;
    private JButton limpiar;
    private JPanel panelparagrafico;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private DatePicker fechaPicker;
    private DatePicker fechaPicker1;


    Controller controller;
    Model model;

    public View() {
        DatePickerSettings settingsDesde = new DatePickerSettings();
        settingsDesde.setFormatForDatesCommonEra("yyyy-MM-dd");
        settingsDesde.setAllowKeyboardEditing(false);
        fechaPicker = new DatePicker(settingsDesde);

        fechaParaDesde.setLayout(new BorderLayout());
        fechaParaDesde.add(fechaPicker, BorderLayout.CENTER);

        DatePickerSettings settingsHasta = new DatePickerSettings();
        settingsHasta.setFormatForDatesCommonEra("yyyy-MM-dd");
        settingsHasta.setAllowKeyboardEditing(false);
        fechaPicker1 = new DatePicker(settingsHasta);

        fechaParaHasta.setLayout(new BorderLayout());
        fechaParaHasta.add(fechaPicker1, BorderLayout.CENTER);

        panelparagrafico.setLayout(new BorderLayout());
        panelparagrafico.setPreferredSize(new Dimension(600, 400));
        panelparagrafico.setVisible(true);


        aceptarButton.addActionListener(e -> actualizarTabla());

        listaMedicamentos.addActionListener(e -> {
            if (fechaPicker.getDate() != null && fechaPicker1.getDate() != null) {
                actualizarTabla();
            }
        });
        limpiar.addActionListener(e -> {
            medicamentosSeleccionados.clear();
            table1.setModel(new TableModel(
                    new int[]{TableModel.MEDICAMENTO, TableModel.DESDE, TableModel.HASTA},
                    new ArrayList<>(), "", ""
            ));
        });
    }

    private void actualizarTabla() {
        LocalDate desde = fechaPicker.getDate();
        LocalDate hasta = fechaPicker1.getDate();
        String nombreMedicamento = (String) listaMedicamentos.getSelectedItem();
        Medicamento seleccionado = buscarMedicamentoPorNombre(nombreMedicamento);

        if (desde == null || hasta == null || seleccionado == null) {
            JOptionPane.showMessageDialog(panelDatos, "Debe seleccionar fechas y un medicamento.");
            return;
        }

        // Evitar duplicados
        if (medicamentosSeleccionados.stream().noneMatch(m -> m.getCodigo().equals(seleccionado.getCodigo()))) {
            medicamentosSeleccionados.add(seleccionado);
        }

        int[] cols = {
                TableModel.MEDICAMENTO,
                TableModel.DESDE,
                TableModel.HASTA
        };

        String fechaDesdeStr = desde.toString();
        String fechaHastaStr = hasta.toString();

        table1.setModel(new TableModel(cols, medicamentosSeleccionados, fechaDesdeStr, fechaHastaStr));
        table1.revalidate();
        table1.repaint();

        try {
            Map<YearMonth, Integer> datos = contarRecetasPorMes(seleccionado, desde, hasta);
            mostrarGraficoRecetas(datos, seleccionado.getNombre());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelDatos, "Error al generar gráfico: " + ex.getMessage());
        }
        System.out.println("Medicamento seleccionado: " + seleccionado.getNombre());
        System.out.println("Desde: " + desde + " | Hasta: " + hasta);
    }

    private Map<YearMonth, Integer> contarRecetasPorMes(Medicamento medicamento, LocalDate desde, LocalDate hasta) throws Exception {
        Map<YearMonth, Integer> conteoPorMes = new TreeMap<>();
        List<Receta> recetas = Hospital.data.XmlPersister.instance().load().getRecetas();

        for (Receta r : recetas) {
            LocalDate fecha = r.getFechaConfeccion();
            if (fecha == null || fecha.isBefore(desde) || fecha.isAfter(hasta)) continue;

            for (Prescripcion p : r.getPrescripciones()) {
                Medicamento m = p.getMedicamento();
                if (m != null && m.getNombre().equalsIgnoreCase(medicamento.getNombre())) {
                    YearMonth mes = YearMonth.from(fecha);
                    conteoPorMes.put(mes, conteoPorMes.getOrDefault(mes, 0) + p.getCantidad());
                    System.out.println("→ Coincidencia: " + m.getNombre());
                    break;
                }
            }
        }

        return conteoPorMes;
    }

    private void mostrarGraficoRecetas(Map<YearMonth, Integer> datos, String nombreMedicamento) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<YearMonth, Integer> entry : datos.entrySet()) {
            dataset.addValue(entry.getValue(), nombreMedicamento, entry.getKey().toString());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Recetas confeccionadas por mes",
                "Mes",
                "Cantidad",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false

        );

        var plot = chart.getCategoryPlot();
        var renderer = (org.jfree.chart.renderer.category.BarRenderer) plot.getRenderer();

        renderer.setMaximumBarWidth(0.01);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));

        panelparagrafico.removeAll();
        panelparagrafico.setLayout(new BorderLayout());
        panelparagrafico.add(chartPanel, BorderLayout.CENTER);
        panelparagrafico.revalidate();
        panelparagrafico.repaint();

        System.out.println("Datos para graficar:");
        for (Map.Entry<YearMonth, Integer> entry : datos.entrySet()) {
            System.out.println("Mes: " + entry.getKey() + " → Cantidad: " + entry.getValue());
        }
    }

    public JPanel getPanel() {
        return panelDatos;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);

        for (Medicamento m : model.getMedicamentos()) {
            listaMedicamentos.addItem(m.getNombre());
        }

        int[] cols = {
                TableModel.MEDICAMENTO,
                TableModel.DESDE,
                TableModel.HASTA
        };
        table1.setModel(new TableModel(cols, new ArrayList<>(), "", ""));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                // Si querés actualizar algo cuando cambie la lista de medicamentos
                break;
            case Model.CURRENT:
                // Si querés mostrar detalles del medicamento actual
                break;
        }
        panelDatos.revalidate();
    }

    private final List<Medicamento> medicamentosSeleccionados = new ArrayList<>();

    private Medicamento buscarMedicamentoPorNombre(String nombre) {
        for (Medicamento m : model.getMedicamentos()) {
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                return m;
            }
        }
        return null;
    }
}