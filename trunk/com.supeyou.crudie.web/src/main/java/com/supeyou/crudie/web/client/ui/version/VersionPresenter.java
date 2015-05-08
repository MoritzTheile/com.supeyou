package com.supeyou.crudie.web.client.ui.version;

import com.supeyou.crudie.web.client.model.AppInfoModel;
import com.supeyou.crudie.web.client.resources.i18n.Text;

public class VersionPresenter extends Panel implements com.supeyou.crudie.web.client.model.AbstrObservable.Observer<AppInfoModel> {

	public VersionPresenter(AppInfoModel model) {

		model.addObserver(this);

	}

	@Override
	public void modelHasChanged(AppInfoModel model) {

		label.setText(Text.i.MULTIUSE_Version() + " " + model.getVersion());

	}
}
