package primitives;
/**
 * class Material, the material of the geometries
 * @author nehora levy and batya lasry
 *
 */
public class Material 
{
	/**
	 * Diffuse factor
	 */
	public double _kD;
	/**
	 * Sepcular factor
	 */
	public double _kS;
	/**
	 * Shininess degree
	 */
	public int _nShininess;
	/**
	 * Refraction factor
	 */
	public double _kT;
	/**
	 * reflection factor
	 */
	public double _kR;

	/**
	 * parameter constructor
	 * @param kD
	 * @param kS
	 * @param nShininess
	 */
	public Material(double kD, double kS, int nShininess)
	{
		this(kD, kS, nShininess, 0, 0);
	}
	/**
	 * additional parameter constructor
	 * @param kD-diffuse
	 * @param kS-Specular
	 * @param nShininess
	 * @param kt-refraction
	 * @param kr-reflection
	 */
	public Material(double kD, double kS, int nShininess, double kt, double kr)
	{
		this._kD = kD;
		this._kS = kS;
		this._nShininess = nShininess;
		this._kT = kt;
		this._kR = kr;

	}
	/**
	 * copy constructor
	 * @param material
	 */
	public Material(Material material)
	{
		this(material._kD, material._kS, material._nShininess, material._kT, material._kR);

	}
	/**
	 * kt getter
	 * @return double
	 */
	public double get_kT()
	{
		return _kT;
	}

	/**
	 * kr getter
	 * @return double
	 */
	public double get_kR()
	{
		return _kR;
	}
	/**
	 * getter of _kD
	 * @return double
	 */
	public double get_kD() {
		return _kD;
	}
	/**
	 * getter of _kS
	 * @return double
	 */
	public double get_kS() {
		return _kS;
	}
	/**
	 * getter of _nShininess
	 * @return int
	 */
	public int get_nShininess() {
		return _nShininess;
	}

}


