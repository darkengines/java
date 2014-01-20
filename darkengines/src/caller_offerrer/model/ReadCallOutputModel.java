package caller_offerrer.model;

import java.util.Map;

import caller_offerrer.Call;
import caller_offerrer.CallType;
import caller_offerrer.Caller;
import caller_offerrer.FixedTermContract;

public class ReadCallOutputModel {
	private Long callId;
	private Map<Long, String> programmingLanguages;
	private Map<Long, String> frameworks;
	private Map<Long, String> languages;
	private String description;
	private Integer seniority;
	private Integer diploma;
	private String email;
	private String phone;
	private Float remuneration;
	private CallType type;
	private int length;
	private String title;
	
	public Long getCallerId() {
		return callId;
	}

	public void setCallerId(Long callerId) {
		this.callId = callerId;
	}

	public Float getRemuneration() {
		return remuneration;
	}

	public void setRemuneration(Float remuneration) {
		this.remuneration = remuneration;
	}

	public CallType getType() {
		return type;
	}

	public void setType(CallType type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setSeniority(Integer seniority) {
		this.seniority = seniority;
	}

	public void setDiploma(Integer diploma) {
		this.diploma = diploma;
	}

	public ReadCallOutputModel(Call call, Caller caller, boolean fillContact) {
		callId = call.getId();
		programmingLanguages = darkengines.set.Util.toMap(call.getProgrammingLanguages());
		frameworks = darkengines.set.Util.toMap(call.getFrameworks());
		languages = darkengines.set.Util.toMap(call.getLanguages());
		description = call.getDescription();
		seniority = call.getSeniority();
		diploma = call.getDiploma();
		remuneration = call.getRemuneration();
		if (call.getCallType() == CallType.FixedTermContract) {
			length = ((FixedTermContract)call).getLength();
		}
		if (fillContact) {
			email = caller.getContact().getEmail();
			phone = caller.getContact().getPhone();
		}
		setTitle(call.getTitle());
		type = call.getCallType();
	}

	public Map<Long, String> getProgrammingLanguages() {
		return programmingLanguages;
	}

	public void setProgrammingLanguages(Map<Long, String> programmingLanguages) {
		this.programmingLanguages = programmingLanguages;
	}

	public Map<Long, String> getFrameworks() {
		return frameworks;
	}

	public void setFrameworks(Map<Long, String> frameworks) {
		this.frameworks = frameworks;
	}

	public Map<Long, String> getLanguages() {
		return languages;
	}

	public void setLanguages(Map<Long, String> languages) {
		this.languages = languages;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSeniority() {
		return seniority;
	}

	public void setSeniority(int seniority) {
		this.seniority = seniority;
	}

	public int getDiploma() {
		return diploma;
	}

	public void setDiploma(int diploma) {
		this.diploma = diploma;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
