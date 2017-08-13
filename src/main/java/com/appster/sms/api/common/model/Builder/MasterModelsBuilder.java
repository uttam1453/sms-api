package com.appster.sms.api.common.model.Builder;

import org.springframework.stereotype.Component;

import com.appster.sms.api.master.model.ListModel;

/**
 * created by atul on 13/01/17.
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
