package com.arwenmc.api.HologramBuilder;

import com.arwenmc.api.FileBuilder.FileBuilder;

public class HologramManager {
    public void loadHologramsFromFile(String path, String file) {
        FileBuilder fb = new FileBuilder(path, file);
        for (String name : fb.getKeys(false)) {
            HologramBuilder hb = new HologramBuilder(name, fb.getString(name + ".World"), fb.getDouble(name + ".X"), fb.getDouble(name + ".Y"), fb.getDouble(name + ".Z"));
            for (String line : fb.getStringList(name + ".Lines")) {
                hb.addLine(line);
            }
            hb.spawn();
        }
    }
}
