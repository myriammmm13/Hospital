package presentation.dashboard;

import logic.Medicamento;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

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
    private JPanel panelparaPastel;
    private JButton actualizarPastel;
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

        panelparaPastel.setPreferredSize(new Dimension(400, 300));
        panelparaPastel.setVisible(true);

        try {
            Map<String, Integer> datos = contarRecetasPorEstado();
            mostrarGraficoEstados(datos);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelDatos, "Error al generar gráfico de estados: " + ex.getMessage());
        }

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

        actualizarPastel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Map<String, Integer> datos = contarRecetasPorEstado();
                    mostrarGraficoEstados(datos);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panelDatos, "Error al actualizar gráfico de estados: " + ex.getMessage());
                }
            }
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
            Map<String, Map<YearMonth, Integer>> datosPorMedicamento = new TreeMap<>();
            for (Medicamento m : medicamentosSeleccionados) {
                Map<YearMonth, Integer> datos = contarRecetasPorMes(m, desde, hasta);
                datosPorMedicamento.put(m.getNombre(), datos);
            }
            mostrarGraficoRecetas(datosPorMedicamento, desde, hasta);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelDatos, "Error al generar gráfico: " + ex.getMessage());
        }
        System.out.println("Medicamento seleccionado: " + seleccionado.getNombre());
        System.out.println("Desde: " + desde + " | Hasta: " + hasta);
    }

    private Map<YearMonth, Integer> contarRecetasPorMes(Medicamento medicamento, LocalDate desde, LocalDate hasta) throws Exception {
        Map<YearMonth, Integer> conteoPorMes = new TreeMap<>();
        //List<Receta> recetas = Hospital.backend.logic.XmlPersister.instance().load().getRecetas();
//cambiar por BD
     /*   for (Receta r : recetas) {
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
        YearMonth inicio = YearMonth.from(desde);
        YearMonth fin = YearMonth.from(hasta);

        YearMonth actual = inicio;
        while (!actual.isAfter(fin)) {
            conteoPorMes.putIfAbsent(actual, 0);
            actual = actual.plusMonths(1);
        }

        return conteoPorMes;*/
        return conteoPorMes;
    }

    private Map<String, Integer> contarRecetasPorEstado() throws Exception {
        Map<String, Integer> conteo = new TreeMap<>();
       /* List<Receta> recetas = Hospital.backend.logic.XmlPersister.instance().load().getRecetas();

        for (Receta r : recetas) {
            String estado = r.getEstado();
            if (estado == null || estado.isBlank()) continue;

            conteo.put(estado, conteo.getOrDefault(estado, 0) + 1);
        }
*/
        return conteo;
    }

    private void mostrarGraficoRecetas(Map<String, Map<YearMonth, Integer>> datosPorMedicamento, LocalDate desde, LocalDate hasta) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        YearMonth inicio = YearMonth.from(desde);
        YearMonth fin = YearMonth.from(hasta);
        List<YearMonth> todosLosMeses = new ArrayList<>();
        YearMonth actual = inicio;
        while (!actual.isAfter(fin)) {
            todosLosMeses.add(actual);
            actual = actual.plusMonths(1);
        }

        for (Map.Entry<String, Map<YearMonth, Integer>> entrada : datosPorMedicamento.entrySet()) {
            String nombre = entrada.getKey();
            Map<YearMonth, Integer> datos = entrada.getValue();

            for (YearMonth mes : todosLosMeses) {
                int cantidad = datos.getOrDefault(mes, 0);
                dataset.addValue(cantidad, nombre, mes.toString());
            }
        }

        JFreeChart chart = ChartFactory.createLineChart(
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
        var renderer = plot.getRenderer();

        Color[] colores = {Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE, Color.MAGENTA};
        for (int i = 0; i < datosPorMedicamento.size(); i++) {
            renderer.setSeriesPaint(i, colores[i % colores.length]);
            renderer.setSeriesStroke(i, new BasicStroke(2.0f));
        }renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        chart.setBackgroundPaint(Color.WHITE);
        plot.setBackgroundPaint(new Color(240, 240, 240));

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));

        panelparagrafico.removeAll();
        panelparagrafico.setLayout(new BorderLayout());
        panelparagrafico.add(chartPanel, BorderLayout.CENTER);
        panelparagrafico.revalidate();
        panelparagrafico.repaint();

        System.out.println("Datos para graficar:");
        for (Map.Entry<String, Map<YearMonth, Integer>> entrada : datosPorMedicamento.entrySet()) {
            String nombre = entrada.getKey();
            Map<YearMonth, Integer> datos = entrada.getValue();

            System.out.println("→ Medicamento: " + nombre);
            for (YearMonth mes : todosLosMeses) {
                int cantidad = datos.getOrDefault(mes, 0);
                dataset.addValue(cantidad, nombre, mes.toString());
                System.out.println("Mes: " + mes + " → Cantidad: " + cantidad);
            }
        }
    }

    private void mostrarGraficoEstados(Map<String, Integer> datos) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (Map.Entry<String, Integer> entry : datos.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }
        JFreeChart chart = ChartFactory.createPieChart(
                "Recetas por estado",
                dataset,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(300, 200));

        panelparaPastel.removeAll();
        panelparaPastel.setLayout(new BorderLayout());
        panelparaPastel.add(chartPanel, BorderLayout.CENTER);
        panelparaPastel.revalidate();
        panelparaPastel.repaint();
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