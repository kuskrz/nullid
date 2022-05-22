package kus.krzysztof.nullid.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Knumbers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Integer number;
	
	@Column
	private Integer testnumber;
	
	public int getId() {
		return id;
	}

	public Integer getTestNumber() {
		return testnumber;
	}

	public void setTestNumber(Integer testNumber) {
		this.testnumber = testNumber;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
