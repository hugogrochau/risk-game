package org.puc.rio.inf1636.hglm.war.model;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import org.puc.rio.inf1636.hglm.war.Util;

public class Map extends Object {

	private List<Territory> territories = new ArrayList<Territory>();
	
	public Map() {

	}

	public void addTerritory(Territory t) {
		this.territories.add(t);
	}

	public List<Territory> getTerritories() {
		return this.territories;
	}	
	
	public void calculateNeighbors() {
        ArrayList<Line2D.Double> tLines = new ArrayList<>();
        ArrayList<Line2D.Double> uLines = new ArrayList<>();
		
		 for (Territory t: territories) {
			 tLines = Util.getLineSegments(t.getPolygon());
			 for (Territory u: territories) {
				 if (!t.equals(u)) {
					 uLines = Util.getLineSegments(u.getPolygon());
					 for (Line2D.Double tl : tLines) {
						 for (Line2D.Double ul: uLines) {
							 if (tl.intersectsLine(ul)) {
								 t.addNeighbor(u);
								 continue;
							 }
						 }
					 }
				 }
			 }
		 }
		// add Neighbors by BRIDGES
		this.CreateBridgeBetweenTerritoriesWithNames("Alasca", "Sibéria");
		this.CreateBridgeBetweenTerritoriesWithNames("Groelandia", "Reino Unido");
		this.CreateBridgeBetweenTerritoriesWithNames("França", "Reino Unido");
		this.CreateBridgeBetweenTerritoriesWithNames("Argélia", "Espanha");
		this.CreateBridgeBetweenTerritoriesWithNames("Argélia", "Itália");
		this.CreateBridgeBetweenTerritoriesWithNames("Suécia", "França");
		this.CreateBridgeBetweenTerritoriesWithNames("Suécia", "Itália");
		this.CreateBridgeBetweenTerritoriesWithNames("Egito", "România");
		this.CreateBridgeBetweenTerritoriesWithNames("Egito", "Jordânia");
		this.CreateBridgeBetweenTerritoriesWithNames("Somália", "Arábia Saudita");
		this.CreateBridgeBetweenTerritoriesWithNames("Japão", "Cazaquistão");
		this.CreateBridgeBetweenTerritoriesWithNames("Japão", "Mongólia");
		this.CreateBridgeBetweenTerritoriesWithNames("Japão", "Coréia do Norte");
		this.CreateBridgeBetweenTerritoriesWithNames("Bangladesh", "Indonésia");
		this.CreateBridgeBetweenTerritoriesWithNames("Índia", "Indonésia");
		this.CreateBridgeBetweenTerritoriesWithNames("Austrália", "Indonésia");
		this.CreateBridgeBetweenTerritoriesWithNames("Austrália", "Nova Zelândia");
		
	}
	private void CreateBridgeBetweenTerritoriesWithNames(String nameX,String nameY)
	{
		Territory x,y;
		x = this.searchTerritoryByName(nameX);
		y = this.searchTerritoryByName(nameY);
		if(x==null||y==null)
		{
			System.out.println("not found" + nameX + " " + nameY);
			return;
		}
		//System.out.println("found " + nameX + " " + nameY);
		x.addNeighbor(y);
		y.addNeighbor(x);
	}
	public Territory searchTerritoryByName(String name)
	{
		for(Territory t : this.territories)
		{
			if(t.getName().equals(name))
			{
				//System.out.println(name + "Found");
				return t;
			}
		}
		System.out.println(name + " notFound");
		return null;
		
	}
		
}
