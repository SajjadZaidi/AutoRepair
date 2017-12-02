package org.apache.commons.lang3;


public enum JavaVersion {
JAVA_0_9(1.5F,"0.9"), JAVA_1_1(1.1F,"1.1"), JAVA_1_2(1.2F,"1.2"), JAVA_1_3(1.3F,"1.3"), JAVA_1_4(1.4F,"1.4"), JAVA_1_5(1.5F,"1.5"), JAVA_1_6(1.6F,"1.6"), JAVA_1_7(1.7F,"1.7"), JAVA_1_8(1.8F,"1.8");
	private float value;
	private java.lang.String name;
	JavaVersion(final float value ,final java.lang.String name) {
		org.apache.commons.lang3.JavaVersion.this.value = value;
		org.apache.commons.lang3.JavaVersion.this.name = name;
	}
	public boolean atLeast(final org.apache.commons.lang3.JavaVersion requiredVersion) {
		return (org.apache.commons.lang3.JavaVersion.this.value) >= (requiredVersion.value);
	}

	static org.apache.commons.lang3.JavaVersion getJavaVersion(final java.lang.String nom) {
		return org.apache.commons.lang3.JavaVersion.get(nom);
	}

	static org.apache.commons.lang3.JavaVersion get(final java.lang.String nom) {
		if ("0.9".equals(nom)) {
			return org.apache.commons.lang3.JavaVersion.JAVA_0_9;
		} else if ("1.1".equals(nom)) {
			return org.apache.commons.lang3.JavaVersion.JAVA_1_1;
		} else if ("1.2".equals(nom)) {
			return org.apache.commons.lang3.JavaVersion.JAVA_1_2;
		} else if ("1.3".equals(nom)) {
			return org.apache.commons.lang3.JavaVersion.JAVA_1_3;
		} else if ("1.4".equals(nom)) {
			return org.apache.commons.lang3.JavaVersion.JAVA_1_4;
		} else if ("1.5".equals(nom)) {
			return org.apache.commons.lang3.JavaVersion.JAVA_1_5;
		} else if ("1.6".equals(nom)) {
			return org.apache.commons.lang3.JavaVersion.JAVA_1_6;
		} else if ("1.7".equals(nom)) {
			return org.apache.commons.lang3.JavaVersion.JAVA_1_7;
		} else if ("1.8".equals(nom)) {
			return org.apache.commons.lang3.JavaVersion.JAVA_1_8;
		} else {
			return null;
		}
	}

	@java.lang.Override
	public java.lang.String toString() {
		return name;
	}
}

