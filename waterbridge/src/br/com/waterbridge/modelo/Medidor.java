package br.com.waterbridge.modelo;

public class Medidor {
	
	private Long idMedidor;
	private Long idCondominio;
	private Long idBridge;
	private Long idUser;
	private String deviceNum;
	private Long idFabricMedidor;
	private FabricMedidor fabricMedidor;
	private String modelo;
	private String serie;
	private String tipo;
	private String chaveDeCripto;
	private int validBateria;
	private String numeroMedidor;
	private int meterPosition;
	private String endereco;
    private Long numero;
    private String compl;
    private String municipio;
    private String uf;
    private String cep;
	private String coordX;
    private String coordY;
	private String obs;
	private String situacao;
	private String dtInsert;
	
	public Long getIdMedidor() {
		return idMedidor;
	}
	public void setIdMedidor(Long idMedidor) {
		this.idMedidor = idMedidor;
	}
	public Long getIdCondominio() {
		return idCondominio;
	}
	public void setIdCondominio(Long idCondominio) {
		this.idCondominio = idCondominio;
	}
	public Long getIdBridge() {
		return idBridge;
	}
	public void setIdBridge(Long idBridge) {
		this.idBridge = idBridge;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getDeviceNum() {
		return deviceNum;
	}
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}
	public Long getIdFabricMedidor() {
		return idFabricMedidor;
	}
	public void setIdFabricMedidor(Long idFabricMedidor) {
		this.idFabricMedidor = idFabricMedidor;
	}
	public FabricMedidor getFabricMedidor() {
		return fabricMedidor;
	}
	public void setFabricMedidor(FabricMedidor fabricMedidor) {
		this.fabricMedidor = fabricMedidor;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getChaveDeCripto() {
		return chaveDeCripto;
	}
	public void setChaveDeCripto(String chaveDeCripto) {
		this.chaveDeCripto = chaveDeCripto;
	}
	public int getValidBateria() {
		return validBateria;
	}
	public void setValidBateria(int validBateria) {
		this.validBateria = validBateria;
	}
	public String getNumeroMedidor() {
		return numeroMedidor;
	}
	public void setNumeroMedidor(String numeroMedidor) {
		this.numeroMedidor = numeroMedidor;
	}
	public int getMeterPosition() {
		return meterPosition;
	}
	public void setMeterPosition(int meterPosition) {
		this.meterPosition = meterPosition;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getCompl() {
		return compl;
	}
	public void setCompl(String compl) {
		this.compl = compl;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCoordX() {
		return coordX;
	}
	public void setCoordX(String coordX) {
		this.coordX = coordX;
	}
	public String getCoordY() {
		return coordY;
	}
	public void setCoordY(String coordY) {
		this.coordY = coordY;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getDtInsert() {
		return dtInsert;
	}
	public void setDtInsert(String dtInsert) {
		this.dtInsert = dtInsert;
	}
	
}
