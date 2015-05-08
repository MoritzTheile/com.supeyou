package com.supeyou.core.web.client.rpc.guest.formmulti.fields;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.core.iface.datatype.enums.TITLE;
import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.core.web.client.fields.types.FieldForTitle;
import com.supeyou.core.web.client.rpc.guest.formmulti.fields.field.FieldWidget;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;
import com.supeyou.crudie.web.client.fields.base.Field;
import com.supeyou.crudie.web.client.fields.types.FieldForDateType;
import com.supeyou.crudie.web.client.fields.types.FieldForSingleLineString256Type;

public class FieldsWidget extends WidgetView {

	private final GuestDTO thisDto;

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	public FieldsWidget(GuestDTO dto) {

		this.thisDto = dto;

		put(

				"Anrede",

				new FieldForTitle(thisDto.getTitle()) {

					@Override
					public void onHasChanged(TITLE value) {
						thisDto.setTitle(value);
						hasChanged();
					}

				}

		);
		put(

				"Vorname",

				new FieldForSingleLineString256Type(thisDto.getFirstname()) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setFirstname(value);
						hasChanged();
					}

				}

		);
		put(

				"Nachname",

				new FieldForSingleLineString256Type(thisDto.getLastname()) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setLastname(value);
						hasChanged();
					}

				}

		);
		put(

				"Geburtstag",

				new FieldForDateType(thisDto.getDateOfBirth()) {

					@Override
					public void onHasChanged(DateType value) {
						thisDto.setDateOfBirth(value);
						hasChanged();
					}

				}

		);
		// asdf private SingleLineString256Type passNr; // GUEST_TABLE_PassNr=Pass Nummer
		put(

				"Passnummer",

				new FieldForSingleLineString256Type(thisDto.getPassNr()) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setPassNr(value);
						;
						hasChanged();
					}

				}

		);
		// asdf private SingleLineString256Type passDate; // GUEST_TABLE_PassDate=Pass Datum
		put(

				"Passdatum",

				new FieldForDateType(thisDto.getPassDate()) {

					@Override
					public void onHasChanged(DateType value) {
						thisDto.setPassDate(value);
						hasChanged();
					}

				}

		);
		// asdf private SingleLineString256Type passAgency; // GUEST_TABLE_PassAgency=Pass Beh�rde
		put(

				"Behoerde",

				new FieldForSingleLineString256Type(thisDto.getPassAgency()) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setPassAgency(value);
						;
						hasChanged();
					}

				}

		);
		// asdf private SingleLineString256Type addressStreet; // GUEST_TABLE_AddressStreet=Stra�e
		put(

				"Strasse",

				new FieldForSingleLineString256Type(thisDto.getAddressStreet()) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setAddressStreet(value);
						;
						hasChanged();
					}

				}

		);
		// asdf private SingleLineString256Type addressPostalCode; // GUEST_TABLE_AddressPostalCode=PLZ
		put(

				"PLZ",

				new FieldForSingleLineString256Type(thisDto.getAddressPostalCode()) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setAddressPostalCode(value);
						;
						hasChanged();
					}

				}

		);
		// asdf private SingleLineString256Type addressCity; // GUEST_TABLE_AddressOrt=Ort
		put(

				"Ort",

				new FieldForSingleLineString256Type(thisDto.getAddressCity()) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setAddressCity(value);
						;
						hasChanged();
					}

				}

		);
		// asdf private SingleLineString256Type comment; // GUEST_TABLE_Comment=Anmerkung
		put(

				"Bemerkung",

				new FieldForSingleLineString256Type(thisDto.getComment()) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setComment(value);
						;
						hasChanged();
					}

				}

		);
		// asdf private SingleLineString256Type telefone;
		put(

				"Telefon",

				new FieldForSingleLineString256Type(thisDto.getTelefone()) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setTelefone(value);
						;
						hasChanged();
					}

				}

		);
		// asdf private SingleLineString256Type email;
		put(

				"E-Mail",

				new FieldForSingleLineString256Type(thisDto.getEmail()) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setEmail(value);
						;
						hasChanged();
					}

				}

		);
		// asdf private SingleLineString256Type otherContact;
		put(

				"Kontakt",

				new FieldForSingleLineString256Type(thisDto.getOtherContact()) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setOtherContact(value);
						hasChanged();
					}

				}

		);
		if (dto.getId() != null) {
			{

				FlowPanel formColumn = new FlowPanel();

				formColumn.add(new Label("Zimmer"));
				formColumn.add(new com.supeyou.core.web.client.rpc.guest2room.assobeditor.AssoBEditorWidget(thisDto, true));

				columnTwo.add(formColumn);
			}

			{

				FlowPanel formColumn = new FlowPanel();

				formColumn.add(new Label("Gruppe/Familie"));
				formColumn.add(new com.supeyou.core.web.client.rpc.guest2guestgroup.assobeditor.AssoBEditorWidget(thisDto, false));

				columnTwo.add(formColumn);
			}
			{

				FlowPanel formColumn = new FlowPanel();

				formColumn.add(new Label("Kostenuebernahmen"));
				formColumn.add(new com.supeyou.core.web.client.rpc.guest2acceptance.assoaeditor.AssoAEditorWidget(thisDto, false));

				columnTwo.add(formColumn);
			}

		}

	}

	protected void put(String name, AbstrCompositeField<?> field) {
		fields.add(field);
		columnOne.add(new FieldWidget(name, field));
	}

	public boolean isDirty() {
		for (Field<?> field : fields) {
			if (field.isDirty()) {
				return true;
			}
		}
		return false;
	}

	public void hasChanged() {
		// can be overwritten
	}

}
