package business.entities;


public class Config {

    private int port;
    private String direccioIP;
    private String nomBases;
    private String usuariAcces;
    private String contrasenyaBases;

    public Config(int port, String direccioIP, String nomBases, String usuariAcces, String contrasenyaBases) {
        this.port = port;
        this.direccioIP = direccioIP;
        this.nomBases = nomBases;
        this.usuariAcces = usuariAcces;
        this.contrasenyaBases = contrasenyaBases;
    }

    public int getPort() { return port; }
    public String getDireccioIP() { return direccioIP; }
    public String getNomBases() { return nomBases; }
    public String getUsuariAcces() { return usuariAcces; }
    public String getContrasenyaBases() { return contrasenyaBases; }

    public void setPort(int port) { this.port = port; }
    public void setDireccioIP(String direccioIP) { this.direccioIP = direccioIP; }
    public void setNomBases(String nomBases) { this.nomBases = nomBases; }
    public void setUsuariAcces(String usuariAcces) { this.usuariAcces = usuariAcces; }
    public void setContrasenyaBases(String contrasenyaBases) { this.contrasenyaBases = contrasenyaBases; }
}