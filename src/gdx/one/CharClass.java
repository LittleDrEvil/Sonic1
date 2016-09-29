/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.one;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class CharClass {
    Sprite sprSonic;
    private TextureAtlas textureAtlas1;
    Animation[] aniSonic = new Animation[6];
    private float elapsedTime = 0;
    SpriteBatch batchSonic;
    
    int nJum, nDir = 0;
    float x, y = 100, dDy, fSY, fSX, fBX = 50, fBY = 30;
    double dGravity, dSpeed;
    
    
    
    public void create(){
        textureAtlas1 = new TextureAtlas(Gdx.files.internal("SonicStillRight.pack"));
        aniSonic[0] = new Animation(1 / 15f, textureAtlas1.getRegions());
        textureAtlas1 = new TextureAtlas(Gdx.files.internal("SonicStillLeft.pack"));
        aniSonic[1] = new Animation(1 / 15f, textureAtlas1.getRegions());
        textureAtlas1 = new TextureAtlas(Gdx.files.internal("SonicRunLeft.pack"));
        aniSonic[2] = new Animation(1 / 15f, textureAtlas1.getRegions());
        textureAtlas1 = new TextureAtlas(Gdx.files.internal("SonicRunRight.pack"));
        aniSonic[3] = new Animation(1 / 15f, textureAtlas1.getRegions());
        textureAtlas1 = new TextureAtlas(Gdx.files.internal("SonicJumpLeft.pack"));
        aniSonic[4] = new Animation(1 / 15f, textureAtlas1.getRegions());
        textureAtlas1 = new TextureAtlas(Gdx.files.internal("SonicJumpRight.pack"));
        aniSonic[5] = new Animation(1 / 15f, textureAtlas1.getRegions());
    }
        
    public void update(){
        
        if (isHit(x, y, 30, 40, 0, 0, Gdx.graphics.getWidth(), 30)) {
            dSpeed = 0;
            nJum = 0;
            y = fSY;
        }
        
        batchSonic.begin();
        
        elapsedTime += Gdx.graphics.getDeltaTime();
        System.out.println(y);
        
        if (nJum == 0) {
            batchSonic.draw(aniSonic[nDir].getKeyFrame(elapsedTime, true), x, y);

        }
        
        if (nJum == 1) {
            if (nDir == 2 || nDir == 1) {
                batchSonic.draw(aniSonic[4].getKeyFrame(elapsedTime, true), x, y);
            }
            if (nDir == 3 || nDir == 0) {
                batchSonic.draw(aniSonic[5].getKeyFrame(elapsedTime, true), x, y);
            }
            
        }
        
        batchSonic.end();

        if (nDir == 2) {
            nDir = 1;
        }
        if (nDir == 3) {
            nDir = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= 3;
            nDir = 2;
            
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += 3;
            nDir = 3;
            
        }

        if (nJum != 1) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                dDy = 3;
                nJum = 1;
            }
        }
    }
    boolean isHit(float nX1, float nY1, float nW1, float nH1, float nX2, float nY2, float nW2, float nH2) {

        if ((((nX1 <= nX2) && (nX1 + nW1 >= nX2))
                || ((nX1 >= nX2) && (nX1 <= nX2 + nW2)))
                && (((nY1 <= nY2) && (nY1 + nH1 >= nY2))
                || ((nY1 >= nY2) && (nY1 <= nY2 + nH2)))) {
            return (true);
        } else {
            return (false);
        }
    }
}
