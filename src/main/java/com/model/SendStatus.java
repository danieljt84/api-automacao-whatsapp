package com.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(schema = "report")
//Classe de controle do envio das informa��es no Whatsapp
public class SendStatus {
	
	@Id
	private Long id;
	@ManyToOne(cascade=CascadeType.PERSIST)  
	@JoinColumn(name = "id")
    @MapsId
	private DataFile datafile;
	private Date data;
	//Envio das fotos
	private boolean send_photo;
	//Envio do relat�rio
	private boolean send_detail;
	
	public DataFile getDatafile() {
		return datafile;
	}
	public void setDatafile(DataFile datafile) {
		this.datafile = datafile;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public boolean isSend_photo() {
		return send_photo;
	}
	public void setSend_photo(boolean send_photo) {
		this.send_photo = send_photo;
	}
	public boolean isSend_detail() {
		return send_detail;
	}
	public void setSend_detail(boolean send_detail) {
		this.send_detail = send_detail;
	}
	
	
    

}
