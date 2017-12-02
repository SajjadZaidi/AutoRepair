// default package (CtPackage.TOP_LEVEL_PACKAGE_NAME in Spoon= unnamed package)



public class EnumUtils {
	public EnumUtils() {
		super();
	}

	public static Enum getEnum(java.lang.Class enumClass, java.lang.String name) {
		return Enum.getEnum(enumClass, name);
	}

	public static ValuedEnum getEnum(java.lang.Class enumClass, int value) {
		return ((ValuedEnum)(ValuedEnum.getEnum(enumClass, value)));
	}

	public static java.util.Map getEnumMap(java.lang.Class enumClass) {
		return Enum.getEnumMap(enumClass);
	}

	public static java.util.List getEnumList(java.lang.Class enumClass) {
		return Enum.getEnumList(enumClass);
	}

	public static java.util.Iterator iterator(java.lang.Class enumClass) {
		return Enum.getEnumList(enumClass).iterator();
	}
}

public class EnumUtils {
	public EnumUtils() {
		super();
	}

	public static org.apache.commons.lang.enum.Enum getEnum(java.lang.Class enumClass, java.lang.String name) {
		return org.apache.commons.lang.enum.Enum.getEnum(enumClass, name);
	}

	public static org.apache.commons.lang.enum.ValuedEnum getEnum(java.lang.Class enumClass, int value) {
		return ((org.apache.commons.lang.enum.ValuedEnum)(org.apache.commons.lang.enum.ValuedEnum.getEnum(enumClass, value)));
	}

	public static java.util.Map getEnumMap(java.lang.Class enumClass) {
		return org.apache.commons.lang.enum.Enum.getEnumMap(enumClass);
	}

	public static java.util.List getEnumList(java.lang.Class enumClass) {
		return org.apache.commons.lang.enum.Enum.getEnumList(enumClass);
	}

	public static java.util.Iterator iterator(java.lang.Class enumClass) {
		return org.apache.commons.lang.enum.Enum.getEnumList(enumClass).iterator();
	}
}

