package br.com.waterbridge.enuns;

import java.util.ArrayList;
import java.util.List;

public enum SexoEnum {
	
	FEMININO("F", "Feminino"),
	MASCULINO("M", "Masculino");

	private String id;
	private String descricao;
	
	
	private SexoEnum(String id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	
	
	public static List<SexoEnum> listCodigos() {
		List<SexoEnum> list = new ArrayList<>();
		for (SexoEnum codigoEnum : values()) {
			list.add(codigoEnum);
		}
		return list;
	}



	public String getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

}
