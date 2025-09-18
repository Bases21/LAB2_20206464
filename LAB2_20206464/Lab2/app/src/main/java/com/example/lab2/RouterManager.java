package com.example.lab2;

import com.example.lab2.models.Router;
import java.util.ArrayList;
import java.util.List;

public class RouterManager {
    private static RouterManager instance;
    private List<Router> routers;

    private RouterManager() {
        routers = new ArrayList<>();
        initializeData();
    }

    public static RouterManager getInstance() {
        if (instance == null) {
            instance = new RouterManager();
        }
        return instance;
    }

    private void initializeData() {
        routers.add(new Router("Cisco", "ISR 4451-X", "1 Gbps", "Operativo"));
        routers.add(new Router("TP-Link", "Archer C6", "2 Gbps", "En reparación"));
        routers.add(new Router("Aruba", "Instant On AP22", "1 Gbps", "Dado de baja"));
        routers.add(new Router("Cisco", "Catalyst 2960-X", "3 Gbps", "Operativo"));
    }

    public List<Router> getRouters() {
        return routers;
    }

    public void addRouter(Router router) {
        routers.add(router);
    }

    public void updateRouter(int position, Router router) {
        if (position >= 0 && position < routers.size()) {
            routers.set(position, router);
        }
    }

    public void removeRouter(int position) {
        if (position >= 0 && position < routers.size()) {
            routers.remove(position);
        }
    }
}