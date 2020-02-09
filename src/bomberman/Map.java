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
	
	public String isOnItem(float x, float y) {
		int tileW = this.map.getTileWidth();
	    int tileH = this.map.getTileHeight();
	    int logicLayer = this.map.getLayerIndex("mur");
	    Image tile = this.map.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
	    if (tile != null) {
	    	return "ui";
	    } else {
	    	return "";
	    }
	}
	
	public boolean isCollision(float futurX, float futurY, boolean onBomb, int numero) {
	    int tileW = this.map.getTileWidth();
	    int tileH = this.map.getTileHeight();
	    int logicLayer = this.map.getLayerIndex("mur");
	    Image tile = this.map.getTileImage((int) futurX / tileW, (int) futurY / tileH, logicLayer);
	    boolean collision;
	    if (tile != null) {
	    	String tileName = tile.getResourceReference();
	    	String mur = "resources/map/../tuiles/mur.png";
	    	String bomb = "resources/map/../tuiles/bomb.png";
	    	String itemBomb = "resources/map/../tuiles/itemBomb.png";
	    	String itemRange = "resources/map/../tuiles/itemRange.png";
	    	String itemSpeed = "resources/map/../tuiles/itemSpeed.png";
	    	if (tileName.equals(mur)) {
		    	collision = true;
		    } else if (onBomb && tileName.equals(bomb)) {
		    	collision = false;
		    } else if(tileName.equals(itemBomb)) {
		    	MapGameState.giveOneMoreBomb(numero);
		    	this.map.setTileId((int) futurX / tileW, (int) futurY / tileH, logicLayer, 0);
		    	collision = false;
		    	MapGameState.addScore(50, numero);
		    } else if (tileName.equals(itemRange)) {
		    	MapGameState.giveRange(numero);
		    	this.map.setTileId((int) futurX / tileW, (int) futurY / tileH, logicLayer, 0);
		    	collision = false;
		    	MapGameState.addScore(50, numero);
		    } else if (tileName.equals(itemSpeed)) {
		    	MapGameState.giveSpeed(numero);
		    	this.map.setTileId((int) futurX / tileW, (int) futurY / tileH, logicLayer, 0);
		    	collision = false;
		    	MapGameState.addScore(50, numero);
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
		//System.out.println("Map : "+MapGameState.TIME/1000);
		//this.map.setTileId(tuileX, tuileY, logicLayer, 5);
	}
	
	public boolean isDead(float x, float y) {
		int tileW = this.map.getTileWidth();
	    int tileH = this.map.getTileHeight();
	    int logicLayer = this.map.getLayerIndex("mur");
	    Image tile = this.map.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
	    if (tile != null && tile.getResourceReference().equalsIgnoreCase("resources/map/../tuiles/explode.png")) {
	    	return true;
	    } else {
	    	return false;
	    }
	}
	
	public void exploserBomb(float x, float y, int range, int player) {
		if (MapGameState.TIME > 500) {
			String itemBomb = "resources/map/../tuiles/itemBomb.png";
	    	String itemRange = "resources/map/../tuiles/itemRange.png";
	    	String itemSpeed = "resources/map/../tuiles/itemSpeed.png";
			int tileW = this.map.getTileWidth();
		    int tileH = this.map.getTileHeight();
		    int tuileX = (int) x / tileW;
		    int tuileY = (int) y / tileH;
		    int logicLayer = this.map.getLayerIndex("mur");
		    this.map.setTileId(tuileX, tuileY, logicLayer, 5);
		    int nombreBlocsDetruit = 0;
		    for (int i = 1;i <= range;i++) {
		    	Image tile = this.map.getTileImage(((int) x / tileW)+i, (int) y / tileH, logicLayer);
		    	if (tile == null) {
		    		this.map.setTileId(tuileX+i, tuileY, logicLayer, 5);
		    	} else if (tile.getResourceReference().equals(itemBomb) || tile.getResourceReference().equals(itemSpeed) || tile.getResourceReference().equals(itemRange)) {
	    			this.map.setTileId(tuileX+i, tuileY, logicLayer, 5);
	    		} else {
		    		if (tile.getResourceReference().equalsIgnoreCase("resources/map/../tuiles/Caisse.png")) {
		    			this.map.setTileId(tuileX+i, tuileY, logicLayer, 5);
		    			nombreBlocsDetruit++;
		    		}
		    		break;
		    	}
		    }
		    for (int i = 1;i <= range;i++) {
		    	Image tile = this.map.getTileImage(((int) x / tileW)-i, (int) y / tileH, logicLayer);
		    	if (tile == null) {
		    		this.map.setTileId(tuileX-i, tuileY, logicLayer, 5);
		    	} else if (tile.getResourceReference().equals(itemBomb) || tile.getResourceReference().equals(itemSpeed) || tile.getResourceReference().equals(itemRange)) {
	    			this.map.setTileId(tuileX-i, tuileY, logicLayer, 5);
	    		} else {
		    		if (tile.getResourceReference().equalsIgnoreCase("resources/map/../tuiles/Caisse.png")) {
		    			this.map.setTileId(tuileX-i, tuileY, logicLayer, 5);
		    			nombreBlocsDetruit++;
		    		}
		    		break;
		    	}
		    }
		    for (int i = 1;i <= range;i++) {
		    	Image tile = this.map.getTileImage((int) x / tileW, ((int) y / tileH)+i, logicLayer);
		    	if (tile == null) {
		    		this.map.setTileId(tuileX, tuileY+i, logicLayer, 5);
		    	} else if (tile.getResourceReference().equals(itemBomb) || tile.getResourceReference().equals(itemSpeed) || tile.getResourceReference().equals(itemRange)) {
	    			this.map.setTileId(tuileX, tuileY+i, logicLayer, 5);
	    		} else {
		    		if (tile.getResourceReference().equalsIgnoreCase("resources/map/../tuiles/Caisse.png")) {
		    			this.map.setTileId(tuileX, tuileY+i, logicLayer, 5);
		    			nombreBlocsDetruit++;
		    		}
		    		break;
		    	}
		    }
		    for (int i = 1;i <= range;i++) {
		    	Image tile = this.map.getTileImage((int) x / tileW, ((int) y / tileH)-i, logicLayer);
		    	if (tile == null) {
		    		this.map.setTileId(tuileX, tuileY-i, logicLayer, 5);
		    	} else if (tile.getResourceReference().equals(itemBomb) || tile.getResourceReference().equals(itemSpeed) || tile.getResourceReference().equals(itemRange)) {
	    			this.map.setTileId(tuileX, tuileY-i, logicLayer, 5);
	    		} else {
		    		if (tile.getResourceReference().equalsIgnoreCase("resources/map/../tuiles/Caisse.png")) {
		    			this.map.setTileId(tuileX, tuileY-i, logicLayer, 5);
		    			nombreBlocsDetruit++;
		    		}
		    		break;
		    	}
		    }
		    MapGameState.addScore((nombreBlocsDetruit * 15), player);
		}
	}
	
	public void clearExplosion(float x, float y, int range) {
		if (MapGameState.TIME > 500) {
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
		    			int itemChance = (int) (Math.random() * ( 100 - 0 ));
		    			if (itemChance < 13) {
		    				int whichItem = (int) (Math.random() * ( 100 - 0 ));
		    				if (whichItem < 33) {
		    					this.map.setTileId(tuileX+i, tuileY, logicLayer, 7);
		    				} else if (whichItem < 66) {
		    					this.map.setTileId(tuileX+i, tuileY, logicLayer, 8);
		    				} else {
		    					this.map.setTileId(tuileX+i, tuileY, logicLayer, 9);
		    				}
		    			} else {
		    				this.map.setTileId(tuileX+i, tuileY, logicLayer, 0);
		    			}
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
		    			int itemChance = (int) (Math.random() * ( 100 - 0 ));
		    			if (itemChance < 13) {
		    				int whichItem = (int) (Math.random() * ( 100 - 0 ));
		    				if (whichItem < 33) {
		    					this.map.setTileId(tuileX-i, tuileY, logicLayer, 7);
		    				} else if (whichItem < 66) {
		    					this.map.setTileId(tuileX-i, tuileY, logicLayer, 8);
		    				} else {
		    					this.map.setTileId(tuileX-i, tuileY, logicLayer, 9);
		    				}
		    			} else {
		    				this.map.setTileId(tuileX-i, tuileY, logicLayer, 0);
		    			}
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
		    			int itemChance = (int) (Math.random() * ( 100 - 0 ));
		    			if (itemChance < 13) {
		    				int whichItem = (int) (Math.random() * ( 100 - 0 ));
		    				if (whichItem < 33) {
		    					this.map.setTileId(tuileX, tuileY+i, logicLayer, 7);
		    				} else if (whichItem < 66) {
		    					this.map.setTileId(tuileX, tuileY+i, logicLayer, 8);
		    				} else {
		    					this.map.setTileId(tuileX, tuileY+i, logicLayer, 9);
		    				}
		    			} else {
		    				this.map.setTileId(tuileX, tuileY+i, logicLayer, 0);
		    			}
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
		    			int itemChance = (int) (Math.random() * ( 100 - 0 ));
		    			if (itemChance < 13) {
		    				int whichItem = (int) (Math.random() * ( 100 - 0 ));
		    				if (whichItem < 33) {
		    					this.map.setTileId(tuileX, tuileY-i, logicLayer, 7);
		    				} else if (whichItem < 66) {
		    					this.map.setTileId(tuileX, tuileY-i, logicLayer, 8);
		    				} else {
		    					this.map.setTileId(tuileX, tuileY-i, logicLayer, 9);
		    				}
		    			} else {
		    				this.map.setTileId(tuileX, tuileY-i, logicLayer, 0);
		    			}
		    		} else {
		    			break;
		    		}
		    	}
		    }
		}
	}
}