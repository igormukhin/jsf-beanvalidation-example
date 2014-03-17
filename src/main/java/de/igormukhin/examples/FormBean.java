package de.igormukhin.examples;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ManagedBean
@RequestScoped
public class FormBean {

	// Docs: http://docs.jboss.org/hibernate/validator/5.1/reference/en-US/html_single
	@Size(min = 3)
	@Pattern(regexp = "0000")
	private String field;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
	public void submit() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Field submitted: " + field));
	}
}
