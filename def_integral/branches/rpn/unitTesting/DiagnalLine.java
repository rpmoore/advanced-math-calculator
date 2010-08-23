package unitTesting;

import defIntegral.Calculate;

/**
 * Test class of a diagonal line 12x + 1/2.
 * @author Ryan Moore
 *
 */
public class DiagnalLine implements Calculate {

	@Override
	public double eval(double index) {
		return 12.0*(index) + (0.5);
	}
}
