/**
 * 
 */
package com.appster.sms.api.master.model;

import com.appster.sms.api.common.model.BaseId;

/**
 * @author lokesh created on 09-Aug-2017
 *
 */
public class ListModel extends BaseId{

	private String value;

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
