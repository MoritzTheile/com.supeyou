package com.hotelorga.core.web.client.rpc.guest2room.chooserlarge.item;

import java.util.Date;

import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.hotelorga.core.iface.dto.Guest2RoomDTO;
import com.hotelorga.core.web.client.rpc.guest2room.RPCCRUDProxy;
import com.hotelorga.foundation.iface.common.HELPER;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private Guest2RoomDTO thisDTO;

	private DateType displayStart;
	private DateType displayEnd;

	public ItemWidget(Guest2RoomDTO dto, DateType displayStart, DateType displayEnd) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		this.displayStart = displayStart;
		this.displayEnd = displayEnd;

	}

	private void render() {

		label.clear();

		// if there is no display scope visualization makes no sense
		if (displayStart != null && displayEnd != null) {
			long microPixelLengthOfDay = itemRoot.getOffsetWidth() * 1000 / getDeltaDays(displayStart, displayEnd);

			long daysFromStart = getDeltaDays(displayStart, thisDTO.getFromDate());

			long durationOfOccupation = getDeltaDays(thisDTO.getFromDate(), thisDTO.getToDate());

			timebar.getElement().getStyle().setPosition(Position.ABSOLUTE);
			timebar.getElement().getStyle().setBottom(3, Unit.PX);
			timebar.getElement().getStyle().setBackgroundColor("#00CCFF");
			timebar.getElement().getStyle().setLeft((microPixelLengthOfDay * daysFromStart) / 1000, Unit.PX);
			timebar.getElement().getStyle().setTop(19, Unit.PX);
			timebar.getElement().getStyle().setWidth((microPixelLengthOfDay * durationOfOccupation) / 1000, Unit.PX);
			timebar.setTitle(renderTitle(thisDTO));
			timebar.add(new Label(renderTitle(thisDTO)));
			itemRoot.getElement().getStyle().setOverflow(Overflow.HIDDEN);
			itemRoot.getElement().getStyle().setPosition(Position.RELATIVE);
			itemRoot.getElement().getStyle().setHeight(24, Unit.PX);

		}

		label.clear();
		label.add(new Label(renderLabel(thisDTO)));

	}

	private String renderLabel(Guest2RoomDTO guest2roomDTO) {
		String description = "";

		description += guest2roomDTO.getDtoA().getFirstname() + " " + guest2roomDTO.getDtoA().getLastname() + ", geb. " + HELPER.date2string(guest2roomDTO.getDtoA().getDateOfBirth());

		return description;
	}

	private String renderTitle(Guest2RoomDTO thisDTO2) {

		return "von " + HELPER.date2string(thisDTO2.getFromDate()) + " bis " + HELPER.date2string(thisDTO2.getToDate());
	}

	private static DateTimeFormat fmt = DateTimeFormat.getFormat(DateType.dateFormat);

	public static long getDeltaDays(DateType from, DateType to) {

		long fromTime = fmt.parse(from.value()).getTime();
		long toTime = fmt.parse(to.value()).getTime();

		long deltaTime = toTime - fromTime;

		// adding half a day to be more robust:
		deltaTime = deltaTime + (12L * 60L * 60L * 1000L);

		return deltaTime / (24L * 60L * 60L * 1000L);

	}

	public static DateType incDateByDays(DateType date, int days) {
		long timestampOfDay = fmt.parse(date.value()).getTime();
		return incDateByDays(timestampOfDay, days);

	}

	public static DateType incDateByDays(long timestampOfDay, int days) {
		long daysInMillisecs = (24L * 60L * 60L * 1000L) * days;
		long incrementedTimestamp = daysInMillisecs + timestampOfDay;
		String incrementedDateString = fmt.format(new Date(incrementedTimestamp));
		return new DateType(incrementedDateString);

	}

	public static void main(String[] args) {
		System.out.println(getDeltaDays(new DateType("2014-01-01"), new DateType("2014-12-31")));
	}

	private CRUDProxyListener<Guest2RoomDTO> listener = new CRUDProxyListener<Guest2RoomDTO>() {

		@Override
		public void wasUpdated(Guest2RoomDTO dto) {

			final String updatedStyle = "updated";

			thisWidget.removeStyleName(updatedStyle);

			if (thisDTO.equals(dto)) {

				thisDTO = dto;

				// without the delay the change in style doesn't get picked up
				new Timer() {

					@Override
					public void run() {

						thisWidget.addStyleName(updatedStyle);

					}
				}.schedule(100);

				render();
			}
		}

		@Override
		public void wasDeleted(Guest2RoomDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(Guest2RoomDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

	@Override
	protected void onLoad() {

		super.onLoad();

		render();
	}
}
