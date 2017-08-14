package com.myschool.sms.api.common.model.Builder;

import org.springframework.stereotype.Component;

import com.myschool.sms.api.master.model.ListModel;

/**
 * created by lokesh on 13/01/17.
 */
@Component
public class MasterModelsBuilder {


	public ListModel getListModel(int id,String value){
		ListModel listModel = new  ListModel();
		listModel.setId(id);
		listModel.setValue(value);
		return listModel;
	}
}
