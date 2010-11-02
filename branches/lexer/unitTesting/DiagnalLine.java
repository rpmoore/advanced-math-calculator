package unitTesting;

import defIntegral.Calculate;

/**
 * Test class of a diagnal line 12x + 1/2.
 * @author Ryan
 *
 */
public class DiagnalLine implements Calculate {

	@Override
	public double eval(double index) {
		return 12.0*(index) + (0.5);
	}

}
