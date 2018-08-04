package org.rafaels.tdconsulting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {
    @SerializedName("dia")
    @Expose
    private String dia;

    @SerializedName("altas")
    @Expose
    private Double altas;

    @SerializedName("simonly")
    @Expose
    private Double simonly;

    @SerializedName("hz_lpd")
    @Expose
    private Double hzLpd;

    @SerializedName("hz")
    @Expose
    private Double hz;

    @SerializedName("adsl")
    @Expose
    private Double adsl;

    @SerializedName("lpd")
    @Expose
    private Double lpd;

    @SerializedName("port_post")
    @Expose
    private Double portPost;

    @SerializedName("migraciones")
    @Expose
    private Double migraciones;

    @SerializedName("star")
    @Expose
    private Double star;

    @SerializedName("une")
    @Expose
    private Double une;

    @SerializedName("adsl_une")
    @Expose
    private Double adslUne;

    @SerializedName("lpd_une")
    @Expose
    private Double lpdUne;

    @SerializedName("hz_une")
    @Expose
    private Double hzUne;

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Double getAltas() {
        return altas;
    }

    public void setAltas(Double altas) {
        this.altas = altas;
    }

    public Double getSimonly() {
        return simonly;
    }

    public void setSimonly(Double simonly) {
        this.simonly = simonly;
    }

    public Double getHzLpd() {
        return hzLpd;
    }

    public void setHzLpd(Double hzLpd) {
        this.hzLpd = hzLpd;
    }

    public Double getHz() {
        return hz;
    }

    public void setHz(Double hz) {
        this.hz = hz;
    }

    public Double getAdsl() {
        return adsl;
    }

    public void setAdsl(Double adsl) {
        this.adsl = adsl;
    }

    public Double getLpd() {
        return lpd;
    }

    public void setLpd(Double lpd) {
        this.lpd = lpd;
    }

    public Double getPortPost() {
        return portPost;
    }

    public void setPortPost(Double portPost) {
        this.portPost = portPost;
    }

    public Double getMigraciones() {
        return migraciones;
    }

    public void setMigraciones(Double migraciones) {
        this.migraciones = migraciones;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    public Double getUne() {
        return une;
    }

    public void setUne(Double une) {
        this.une = une;
    }

    public Double getAdslUne() {
        return adslUne;
    }

    public void setAdslUne(Double adslUne) {
        this.adslUne = adslUne;
    }

    public Double getLpdUne() {
        return lpdUne;
    }

    public void setLpdUne(Double lpdUne) {
        this.lpdUne = lpdUne;
    }

    public Double getHzUne() {
        return hzUne;
    }

    public void setHzUne(Double hzUne) {
        this.hzUne = hzUne;
    }

}
