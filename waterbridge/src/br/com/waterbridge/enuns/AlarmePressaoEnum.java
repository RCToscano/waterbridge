package br.com.waterbridge.enuns;

import java.util.ArrayList;
import java.util.List;

public enum AlarmePressaoEnum {
	
	PRESSAO_BAIXA_CRITICO(1, "Pressão está baixa (Nível Crítico)"),
	PRESSAO_BAIXA(2, "Pressão está baixa"),
	PRESSAO_ALTA_CRITICO(3, "Pressão está alta (Nível Crítico)"),
	PRESSAO_ALTA(4, "Pressão está alta");

	private int id;
	private String descricao;
	
	
	private AlarmePressaoEnum(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	
	
	public static List<AlarmePressaoEnum> listCodigos() {
		List<AlarmePressaoEnum> list = new ArrayList<>();
		for (AlarmePressaoEnum codigoEnum : values()) {
			list.add(codigoEnum);
		}
		return list;
	}



	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

}
