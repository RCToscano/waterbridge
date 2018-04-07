package br.com.waterbridge.modelo;

public class Message {

	private Long idMessage;
	private String texto;
	//	time (int): the event timestamp (in seconds since the Unix Epoch)
//	severity (string): the severity of the event (between DEBUG, INFO, WARN, ERROR and FATAL)
//	sourceType (string): the type of source at the origin of the event (between BASE_STATION, DEVICE, CONTRACT, CERTIFICATE, GROUP, PARTNER and NEWS)
//	sourceId (string): the identifier of the source of the event (in hexadecimal string)
//	eventType (string): the event type triggered
	private Long time;
	private String severity;
	private String sourceType;
	private String sourceId;
	private String eventType;

	public Long getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
}
