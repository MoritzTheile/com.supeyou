package com.supeyou.crudie.web.client.rpc.abstr.list;

public class ListPager {

	public final AbstrListDataProvider<?, ?> cwxDataProvider;

	public ListPager(AbstrListDataProvider<?, ?> cwxDataProvider) {

		this.cwxDataProvider = cwxDataProvider;

	}

	public void goToStart() {
		cwxDataProvider.setStartRow(0);
		cwxDataProvider.fetchData();
	}

	public void goToEnd() {
		cwxDataProvider.setStartRow(cwxDataProvider.getTotalSize() - cwxDataProvider.getPageSize());
		cwxDataProvider.fetchData();
	}

	public void goToNextPage() {

		int newStartRow = cwxDataProvider.getStartRow() + cwxDataProvider.getPageSize();

		if (newStartRow >= cwxDataProvider.getTotalSize()) {
			return;

		}
		cwxDataProvider.setStartRow(newStartRow);

		cwxDataProvider.fetchData();
	}

	public void goToPreviousPage() {

		int newStartRow = cwxDataProvider.getStartRow() - cwxDataProvider.getPageSize();

		if (newStartRow < 0) {
			newStartRow = 0;
		}

		cwxDataProvider.setStartRow(newStartRow);

		cwxDataProvider.fetchData();
	}

}
