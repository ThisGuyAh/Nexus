package com.codex.nexus.model;

import com.codex.nexus.math.Vector4;

public class Material {

	private String name;
	private Vector4 ambientColor;
	private Vector4 diffuseColor;
	private Vector4 specularColor;

	public Material(String name, Vector4 ambientColor, Vector4 diffuseColor, Vector4 specularColor) {
		this.name = name;
		this.ambientColor = ambientColor;
		this.diffuseColor = diffuseColor;
		this.specularColor = specularColor;
	}
	
	public String getName() {
		return name;
	}

	public Vector4 getAmbientColor() {
		return ambientColor;
	}

	public Vector4 getDiffuseColor() {
		return diffuseColor;
	}

	public Vector4 getSpecularColor() {
		return specularColor;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAmbientColor(Vector4 ambientColor) {
		this.ambientColor = ambientColor;
	}
	
	public void setDiffuseColor(Vector4 diffuseColor) {
		this.diffuseColor = diffuseColor;
	}
	
	public void setSpecularColor(Vector4 specularColor) {
		this.specularColor = specularColor;
	}

}
