package com.hotelorga.foundation.web.client.ui.version;

import com.hotelorga.foundation.web.client.model.AppInfoModel;
import com.hotelorga.foundation.web.client.resources.i18n.Text;

public class VersionPresenter extends Panel implements com.hotelorga.foundation.web.client.model.AbstrObservable.Observer<AppInfoModel> {

	public VersionPresenter(AppInfoModel model) {

		model.addObserver(this);

	}

	@Override
	public void modelHasChanged(AppInfoModel model) {

		label.setText(Text.i.MULTIUSE_Version() + " " + model.getVersion());

	}
}
