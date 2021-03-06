package gui;

import java.text.NumberFormat;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class NumberFormatterFactory extends DefaultFormatterFactory
{
	private static NumberFormatter numberFormatter = new NumberFormatter();
	  static {
	    numberFormatter.setValueClass(Double.class);
	    ((NumberFormat) numberFormatter.getFormat()).setGroupingUsed(false);
	  }
	  public NumberFormatterFactory() {
	    super(numberFormatter, numberFormatter, numberFormatter);
	  }
}
