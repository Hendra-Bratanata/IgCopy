package com.ichirotech.igcopy;

import org.json.JSONException;
import org.json.JSONObject;

class Jadwal {
    String id ;
    String judud;
    String hari;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudud() {
        return judud;
    }

    public void setJudud(String judud) {
        this.judud = judud;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getHalaman() {
        return halaman;
    }

    public void setHalaman(String halaman) {
        this.halaman = halaman;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    String halaman;
    String gambar;

    Jadwal(JSONObject object){
        try {
            String id = object.getString("id");
            String judul = object.getString("judul");
            String hari = object.getString("hari");
            String gambar = object.getString("gambar");
            String halaman = object.getString("halaman");

            this.id = id;
            this.judud = judul;
            this.hari = hari;
            this.halaman =halaman;
            this.gambar =gambar;

        }catch (JSONException e){
            e.printStackTrace();
        }


    }

}
