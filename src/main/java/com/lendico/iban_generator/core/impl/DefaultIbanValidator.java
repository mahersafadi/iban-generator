package com.lendico.iban_generator.core.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.reflect.ClassPath;
import com.lendico.iban_generator.IbanException;
import com.lendico.iban_generator.core.IbanValidator;
import com.lendico.iban_generator.countries.CountriesLoader;
import com.lendico.iban_generator.countries.CountryMeta;

/**
 * This class is the default Iban validator, it applies list of
 * {@link Validator} against the passed IBAN code and just one exception is
 * enough to declare the the passed IBAN is invalid <br/>
 * Lendico
 * 
 * @author maher
 */
public class DefaultIbanValidator implements IbanValidator {
	protected Logger logger = LoggerFactory.getLogger(IbanValidator .class);
	protected CountriesLoader countriesLoader;

	public DefaultIbanValidator(CountriesLoader countriesLoader) {
		this.countriesLoader = countriesLoader;
	}

	Set<Validator> validators;
	private Boolean b = new Boolean(true);

	public void clearValidator() {
		synchronized (b) {
			if (this.validators != null)
				this.validators.clear();
		}
	}

	protected Set<Validator> getValidators() throws IOException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		if (validators == null || validators.isEmpty()) {
			synchronized (b) {
				if (validators == null || validators.isEmpty()) {
					validators = new HashSet<Validator>();
					final ClassLoader loader = this.getClass().getClassLoader();
					for (final ClassPath.ClassInfo info : ClassPath
							.from(loader).getTopLevelClasses()) {

						logger.info(info.getName());
						Class<?> _class = null;
						try {
							_class = info.load();
						} catch (Throwable e) {
							logger.warn(e.getMessage());
						}
						if (_class != null)
							for (Class<?> _interface : _class.getInterfaces())
								if (_interface
										.getName()
										.equals("com.lendico.iban_generator.core.impl.Validator")) {
									Object o = _class.newInstance();
									validators.add((Validator) o);
								}
					}
				}
			}
		}
		return validators;
	}

	@Override
	public synchronized void validate(String code) throws IbanException {
		if (code == null || code.equals(""))
			throw new IllegalArgumentException();
		String countryCode = code.substring(0, 2);
		CountryMeta countryMeta = countriesLoader.getCountryMetas(countryCode);
		if (countryMeta == null)
			throw new InvalidIbanException(
					InvalidIbanException.Messages.NOT_SUPPORTED_COUNTRY_CODE
							.value());
		try {
			Set<Validator> validators = this.getValidators();
			if (validators == null || validators.isEmpty())
				throw new IbanException(
						"there is not validators, must exist at leat one class implements com.lendico.iban_generator.core.impl.Validator inerface");
			for (Validator v : validators)
				v.apply(code, countryMeta);
		} catch (InstantiationException | IllegalAccessException | IOException
				| ClassNotFoundException e) {
			throw new InvalidIbanException(e.getMessage());
		}
	}

}
