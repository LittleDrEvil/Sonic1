package gdx.one;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class GdxOne extends ApplicationAdapter {

    Sprite sprSonic;
    private TextureAtlas textureAtlas1;
    Animation[] aniSonic = new Animation[6];
    private float elapsedTime = 0;
    SpriteBatch batchSonic, batchBackground;
    Texture imgFloor, imgBack, imgBlock;
    int nJum, nDir = 0;
    float x, y = 100, dDy, fSY, fSX, fBX=50, fBY=30;
    double dGravity, dSpeed;
    Vector2 vSonic;

    @Override
    public void create() {
        imgBack = new Texture(Gdx.files.internal("background.png"));
        imgFloor = new Texture(Gdx.files.internal("background1.png"));
        dGravity = -0.01;
        batchBackground = new SpriteBatch();
        batchSonic = new SpriteBatch();
        imgBlock = new Texture(Gdx.files.internal("block.png"));
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

    @Override
    public void dispose() {
        batchSonic.dispose();
        textureAtlas1.dispose();
    }

    @Override
    public void render() {
        System.out.println(x + " " + y);
        batchBackground.begin();
        batchBackground.draw(imgBack, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batchBackground.draw(imgFloor, 0, 0, Gdx.graphics.getWidth(), 40);
        batchBackground.draw(imgBlock, fBX, fBY, 30, 30);
        batchBackground.end();
        fSY = y;
        fSX = x;
        dDy += dSpeed;
        dSpeed += dGravity;
        y += dDy;
        
        if (isHit(x, y, 30, 40, 0, 0, Gdx.graphics.getWidth(), 30) ){
            dSpeed = 0;
            nJum = 0;
            y = fSY;
            x = fSX;
        }
        if(isHitBlock(x, y, 30, fBX, fBY, 30)) {
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

    boolean isHitBlock(float nX1, float nY1, float nS1, float nX2, float nY2, float nS2) {

        if ((((nX1 <= nX2) && (nX1 + nS1 >= nX2))
                || ((nX1 >= nX2) && (nX1 <= nX2 + nS2)))
                && (((nY1 <= nY2) && (nY1 + nS1 >= nY2))
                || ((nY1 >= nY2) && (nY1 <= nY2 + nS2)))) {
            return (true);
        } else {
            return (false);
        }
    }

    boolean isHit(float nX1, float nY1, float nW1,float nH1, float nX2, float nY2,float nW2, float nH2) {

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