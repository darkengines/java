package application;

import javax.persistence.Entity;

import darkengines.database.IdentifiedEntity;

@Entity
public class PostalCode extends IdentifiedEntity {
	String code;
}
