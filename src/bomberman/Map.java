package bomberman;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
	private TiledMap map;

	public void init() throws SlickException {
		this.map = new TiledMap("resources/map/map.tmx");
	}
	
	public void render() {
		this.map.render(0, 0);
	}
	
	public boolean isOnBomb(float x, float y) {
		int tileW = this.map.getTileWidth();
	    int tileH = this.map.getTileHeight();
	    int logicLayer = this.map.getLayerIndex("mur");
	    Image tile = this.map.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
	    if (tile != null) {
	    	if (tile.getResourceReference().equalsIgnoreCase("resources/map/../tuiles/bomb.png")) {
	    		
		    	return true;
		    } else {
		    	return false;
		    }
	    } else {
	    	return false;
	    }
	}
	
	public boolean isCollision(float futurX, float futurY, boolean onBomb) {
	    int tileW = this.map.getTileWidth();
	    int tileH = this.map.getTileHeight();
	    int logicLayer = this.map.getLayerIndex("mur");
	    Image tile = this.map.getTileImage((int) futurX / tileW, (int) futurY / tileH, logicLayer);
	    boolean collision;
	    if (tile != null) {
	    	if (tile.getResourceReference().equalsIgnoreCase("resources/map/../tuiles/mur.png")) {
		    	collision = true;
		    } else if (onBomb && tile.getResourceReference().equalsIgnoreCase("resources/map/../tuiles/bomb.png")) {
		    	collision = false;
		    } else {
		    	collision = tile != null;
		    }
	    } else {
	    	collision = false;
	    }
	    
	    if (collision) {
	        Color color = tile.getColor((int) futurX % tileW, (int) futurY % tileH);
	        collision = color.getAlpha() > 0;
	    }
	    return collision;
	}
	
	public void poserBomb(float x, float y) {
		int tileW = this.map.getTileWidth();
	    int tileH = this.map.getTileHeight();
	    int tuileX = (int) x / tileW;
	    int tuileY = (int) y / tileH;
	    int logicLayer = this.map.getLayerIndex("mur");
	    this.map.setTileId(tuileX, tuileY, logicLayer, 4);
		//System.out.println("Map : "+WindowGame.TIME/1000);
		//this.map.setTileId(tuileX, tuileY, logicLayer, 5);
	}
	
	public void exploserBomb(float x, float y, int range) {
		int tileW = this.map.getTileWidth();
	    int tileH = this.map.getTileHeight();
	    int tuileX = (int) x / tileW;
	    int tuileY = (int) y / tileH;
	    int logicLayer = this.map.getLayerIndex("mur");
	    this.map.setTileId(tuileX, tuileY, logicLayer, 5);
	    for (int i = 1;i <= range;i++) {
	    	Image tile = this.map.getTileImage(((int) x / tileW)+i, (int) y / tileH, logicLayer);
	    	if (tile == null) {
	    		this.map.setTileId(tuileX+i, tuileY, logicLayer, 5);
	    	} else {
	    		System.out.println(tile.getResourceReference());
	    		if (tile.getResourceReference().equalsIgnoreCase("resources/map/../tuiles/Caisse.png")) {
	    			this.map.setTileId(tuileX+i, tuileY, logicLayer, 5);
	    		}
	    		break;
	    	}
	    }
	    for (int i = 1;i <= range;i++) {
	    	Image tile = this.map.getTileImage(((int) x / tileW)-i, (int) y / tileH, logicLayer);
	    	if (tile == null) {
	    		this.map.setTileId(tuileX-i, tuileY, logicLayer, 5);
	    	} else {
	    		System.out.println(tile.getResourceReference());
	    		if (tile.getResourceReference().equalsIgnoreCase("resources/map/../tuiles/Caisse.png")) {
	    			this.map.setTileId(tuileX-i, tuileY, logicLayer, 5);
	    		}
	    		break;
	    	}
	    }
	    for (int i = 1;i <= range;i++) {
	    	Image tile = this.map.getTileImage((int) x / tileW, ((int) y / tileH)+i, logicLayer);
	    	if (tile == null) {
	    		this.map.setTileId(tuileX, tuileY+i, logicLayer, 5);
	    	} else {
	    		System.out.println(tile.getResourceReference());
	    		if (tile.getResourceReference().equalsIgnoreCase("resources/map/../tuiles/Caisse.png")) {
	    			this.map.setTileId(tuileX, tuileY+i, logicLayer, 5);
	    		}
	    		break;
	    	}
	    }
	    for (int i = 1;i <= range;i++) {
	    	Image tile = this.map.getTileImage((int) x / tileW, ((int) y / tileH)-i, logicLayer);
	    	if (tile == null) {
	    		this.map.setTileId(tuileX, tuileY-i, logicLayer, 5);
	    	} else {
	    		System.out.println(tile.getResourceReference());
	    		if (tile.getResourceReference().equalsIgnoreCase("resources/map/../tuiles/Caisse.png")) {
	    			this.map.setTileId(tuileX, tuileY-i, logicLayer, 5);
	    		}
	    		break;
	    	}
	    }
	}
	
	public void clearExplosion(float x, float y, int range) {
		int tileW = this.map.getTileWidth();
	    int tileH = this.map.getTileHeight();
	    int tuileX = (int) x / tileW;
	    int tuileY = (int) y / tileH;
	    int logicLayer = this.map.getLayerIndex("mur");
	    this.map.setTileId(tuileX, tuileY, logicLayer, 0);
	    for (int i = 1;i <= range;i++) {
	    	Image tile = this.map.getTileImage(((int) x / tileW)+i, (int) y / tileH, logicLayer);
	    	if (tile == null) {
	    		break;
	    	} else {
	    		if (tile.getResourceReference().equalsIgnoreCase("resources/map/../tuiles/explode.png")) {
	    			this.map.setTileId(tuileX+i, tuileY, logicLayer, 0);
	    		} else {
	    			break;
	    		}
	    	}
	    }
	    for (int i = 1;i <= range;i++) {
	    	Image tile = this.map.getTileImage(((int) x / tileW)-i, (int) y / tileH, logicLayer);
	    	if (tile == null) {
	    		break;
	    	} else {
	    		if (tile.getResourceReference().equalsIgnoreCase("resources/map/../tuiles/explode.png")) {
	    			this.map.setTileId(tuileX-i, tuileY, logicLayer, 0);
	    		} else {
	    			break;
	    		}
	    	}
	    }
	    for (int i = 1;i <= range;i++) {
	    	Image tile = this.map.getTileImage((int) x / tileW, ((int) y / tileH)+i, logicLayer);
	    	if (tile == null) {
	    		break;
	    	} else {
	    		if (tile.getResourceReference().equalsIgnoreCase("resources/map/../tuiles/explode.png")) {
	    			this.map.setTileId(tuileX, tuileY+i, logicLayer, 0);
	    		} else {
	    			break;
	    		}
	    	}
	    }
	    for (int i = 1;i <= range;i++) {
	    	Image tile = this.map.getTileImage((int) x / tileW, ((int) y / tileH)-i, logicLayer);
	    	if (tile == null) {
	    		break;
	    	} else {
	    		if (tile.getResourceReference().equalsIgnoreCase("resources/map/../tuiles/explode.png")) {
	    			this.map.setTileId(tuileX, tuileY-i, logicLayer, 0);
	    		} else {
	    			break;
	    		}
	    	}
	    }
	}
}