package Hospital.logic;

import Hospital.data.Data;

//recreado del código del profe
public class Service {
    private static Service Instance;

    public static Service instance() {
        if (Instance == null) Instance = new Service();
        return Instance;
    }

    private Data data;

    private Service() {
        data = new Data();
    }
}