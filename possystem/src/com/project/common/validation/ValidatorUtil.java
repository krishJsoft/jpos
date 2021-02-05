package com.project.common.validation;

import java.util.Calendar;

import org.apache.commons.lang.NumberUtils;

import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.constants.StringConstants;
import com.project.common.factory.ErrorFactory;
import com.project.common.factory.MessageFactory;
import com.project.common.util.ConvertUtil;



/*
 * * @author Gopal
 * @version 1.0
 * @since 08 Aug 2012
 * 
 */

public class ValidatorUtil extends ValidatorBassUtil {

	private ValidatorUtil() {

	}

	@SuppressWarnings("deprecation")
	public static String isValidNewIC(String nric) {

		String isValid = StringConstants.VALID;
		Configuration config = Configuration.getConfiguration();
		int nricLength = Integer.parseInt(config
				.getValue(IConfiguration.NRIC_LENGTH));

		if (nric.length() < nricLength) {
			return isValid = errorFactory.getError("errors.length",
					messageFactory.getMessage("common.newicno"),
					String.valueOf(nricLength));
		}

		if (!NumberUtils.isDigits(nric)) {
			return isValid = errorFactory.getError("errors.numeric",
					messageFactory.getMessage("common.newicno"));
		}

		if (!isValidNricAddYear(nric)) {
			return isValid = errorFactory.getError("errors.numeric",
					messageFactory.getMessage("common.newicno"));
		}

		if (!isValidNricBirthPlace(nric)) {
			return isValid = errorFactory.getError("errors.format",
					messageFactory.getMessage("common.newicno"));
		}

		return isValid;
	}

	public static String isValidPassportNo(String passportNo) {
		String isValid = StringConstants.VALID;
		Configuration config = Configuration.getConfiguration();

		int passportMaxLength = Integer.parseInt(config
				.getValue(IConfiguration.PASSPORT_MAXLENGTH));
		ErrorFactory errorFactory = ErrorFactory.getInstance();
		if (isValidAlphaText(passportNo) || isValidNumber(passportNo)) {
			return isValid = errorFactory.getError("errors.alphanumeric",
					MessageFactory.getInstance()
							.getMessage("common.passportno"));
		}
		if (!isValidAlphaNumeric(passportNo)) {
			return isValid = errorFactory.getError("errors.alphanumeric",
					MessageFactory.getInstance()
							.getMessage("common.passportno"));
		} else if (passportNo.length() > passportMaxLength) {
			isValid = errorFactory.getError("errors.length.more",
					MessageFactory.getInstance()
							.getMessage("common.passportno"), String
							.valueOf(passportMaxLength));
		}

		return isValid;
	}

	public static String isValidOldIC(String value) {
		String isValid = StringConstants.VALID;

		Configuration config = Configuration.getConfiguration();
		MessageFactory messageFactory = MessageFactory.getInstance();

		int maxLength = config.getIntValue(IConfiguration.OLDIC_MAX_LENGTH);

		if (OldICNumberOnlyFormater(value)) {
			maxLength = config.getIntValue(IConfiguration.OLDIC_MIN_LENGTH);
		} else if (OldICAlphaNumericFormater(value)) {
			maxLength = config.getIntValue(IConfiguration.OLDIC_MAX_LENGTH);
		}

		if (value.length() < maxLength) {
			isValid = errorFactory.getError("errors.length",
					messageFactory.getMessage("common.oldicno"),
					String.valueOf(maxLength));
		} else if (value.length() > maxLength) {
			isValid = errorFactory.getError("errors.length.more",
					messageFactory.getMessage("common.oldicno"),
					String.valueOf(maxLength));
		} else if (!OldICFormater(value)) {
			isValid = errorFactory.getError("errors.format",
					messageFactory.getMessage("common.oldicno"));
		}

		return isValid;
	}

	public static String isValidArmyPoliceID(String value) {

		String isValid = StringConstants.VALID;
		Configuration config = Configuration.getConfiguration();
		int minLength = Integer.parseInt(config
				.getValue(IConfiguration.ARMYNO_MINLENGTH));
		int maxLength = Integer.parseInt(config
				.getValue(IConfiguration.ARMYNO_MAXLENGTH));

		if (value.length() < minLength) {
			isValid = errorFactory.getError("errors.length",
					messageFactory.getMessage("common.armypoliceno"),
					String.valueOf(minLength));
		} else if (value.length() > maxLength) {
			isValid = errorFactory.getError("errors.length.more",
					messageFactory.getMessage("common.armypoliceno"),
					String.valueOf(maxLength));
		} else if (!ArmyPoliceIDFormater(value)) {
			isValid = errorFactory.getError("errors.format",
					messageFactory.getMessage("common.armypoliceno"));
		}

		return isValid;
	}
	
	public static String isValidPostCode(String postCode) {
		String isValid = StringConstants.VALID;
		Configuration config = Configuration.getConfiguration();
		int minlength = config.getIntValue(IConfiguration.POSTCODE_LENGTH);
		if (postCode.length() < minlength) {
			isValid = errorFactory.getError("errors.length", messageFactory
					.getMessage("customercardholder.label.postCode"), config
					.getValue(IConfiguration.POSTCODE_LENGTH));
		}
		return isValid;
	}

	public static String isValidCreditCardType(String creditcrNo,
			String cardType) {

		String isValid = "valid";
		Configuration config = Configuration.getConfiguration();
		if (ValidatorUtil.isNull(creditcrNo)) {

			isValid = errorFactory.getError("errors.length",
					"CreditCard Number");
		} else {

			if (cardType.equals((Configuration.PAYMENT_TYPE_MASTER_CARD))) {

				if (creditcrNo.length() < Integer
						.parseInt(config
								.getValue(IConfiguration.PAYMENT_CREDIT_DEBIT_CHARGE_CARD_NO_LENGTH))) {
					isValid = errorFactory.getError("errors.length",
							"CreditCard Number");
				}

				else if (creditcrNo.length() > Integer
						.parseInt(config
								.getValue(IConfiguration.PAYMENT_CREDIT_DEBIT_CHARGE_CARD_NO_LENGTH))) {
					isValid = errorFactory.getError("errors.length.more",
							"CreditCard Number");
				} else if (!creditcrNo.startsWith(config
						.getValue(IConfiguration.CREDITCARD_MASTERS_START))) {
					isValid = errorFactory.getError("errors.prefix",
							"CreditCard Number");

				} else {

				}
			} else if (cardType.equals(Configuration.PAYMENT_TYPE_VISA)) {
				if (creditcrNo.length() < config
						.getIntValue(IConfiguration.PAYMENT_CREDIT_DEBIT_CHARGE_CARD_NO_LENGTH)) {
					isValid = errorFactory.getError("errors.length",
							"CreditCard Number");
				} else if (creditcrNo.length() > config
						.getIntValue(IConfiguration.PAYMENT_CREDIT_DEBIT_CHARGE_CARD_NO_LENGTH)) {
					isValid = errorFactory.getError("errors.length.more",
							"CreditCard Number");
				} else if (!creditcrNo.startsWith(config
						.getValue(IConfiguration.CREDITCARD_VISA_START))) {
					isValid = errorFactory.getError("errors.prefix",
							"CreditCard Number");
				} else {

				}
			} else {
				isValid = "Wrong Card Type";
			}
		}

		return isValid;
	}

	public static String isValidCCExpDate(String mon, String year) {

		long monVal = 0;
		long yearVal = 0;
		String isValid = "valid";
		Configuration config = Configuration.getConfiguration();
		int maxMonth = Integer.parseInt(config
				.getValue(IConfiguration.PAYMENT_CARD_MONTH));
		if (ValidatorUtil.isValidNumber(mon)) {
			monVal = ConvertUtil.toLong(mon);
		}
		if (ValidatorUtil.isValidNumber(year)) {
			yearVal = ConvertUtil.toLong(year);
		}
		int curyear = Calendar.getInstance().get(Calendar.YEAR);
		String tempYear = ConvertUtil.toString(curyear);
		tempYear = tempYear.substring(2);
		curyear = ConvertUtil.toInt(tempYear);
		int curMonth = Calendar.getInstance().get(Calendar.MONTH);

		if ((!ValidatorUtil.isValidNumber(mon))
				&& (!ValidatorUtil.isValidNumber(year))) {
			isValid = errorFactory.getError("errors.numeric",
					"CreditCard Expairy Date");
		} else if (!ValidatorUtil.isValidNumber(mon)) {
			isValid = errorFactory.getError("errors.numeric",
					"CreditCard Expairy Date");

		} else if (!ValidatorUtil.isValidNumber(year)) {
			isValid = errorFactory.getError("errors.numeric",
					"CreditCard Expairy Date");
		} else if (yearVal > 99) {
			isValid = errorFactory.getError("errors.numeric",
					"CreditCard Expairy Date");
		} else if ((yearVal < curyear && monVal < curMonth + 1)) {
			isValid = errorFactory.getError("errors.numeric",
					"CreditCard Expairy Date");
		} else if (yearVal < curyear) {
			isValid = errorFactory.getError("errors.numeric",
					"CreditCard Expairy Date");
		}

		else if ((yearVal == curyear && monVal < curMonth + 1)) {
			isValid = errorFactory.getError("errors.numeric",
					"CreditCard Expairy Date");
		} else if (monVal > maxMonth) {
			isValid = errorFactory.getError("errors.numeric",
					"CreditCard Expairy Date");
		}
		return isValid;
	}

}
