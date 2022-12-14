package Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.team13.piazzapanic.MainGame;
public class Chef extends Sprite {
    public World world;
    public Body b2body;

    private float chefWidth;
    private float chefHeight;
    private Texture wholeImage;
    public enum State {UP, DOWN, LEFT, RIGHT}
    public State currentState;
    public TextureRegion currentSkin;
    public Chef(World world) {
        wholeImage = new Texture("Chef_skins.png");
        this.world = world;
        currentState = State.DOWN;

        defineChef();
        currentSkin = new TextureRegion(wholeImage, 32, 0, 29 ,46);

        chefWidth =  13/MainGame.PPM;
        chefHeight =  20/MainGame.PPM;
        setBounds(0,0,chefWidth, chefHeight);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        currentSkin= getSkin(dt);
        setRegion(currentSkin);
    }

    private TextureRegion getSkin(float dt) {
        currentState = getState();

        TextureRegion region;
        switch(currentState) {
            case UP:
                region = new TextureRegion(wholeImage, 0, 0, 29, 46);
                break;
            case DOWN:
                region = new TextureRegion(wholeImage, 32, 0, 29, 46);
                break;
            case LEFT:
                region = new TextureRegion(wholeImage, 64, 0, 29, 46);
                break;
            case RIGHT:
                region = new TextureRegion(wholeImage, 96, 0, 29, 46);
                break;
            default:
                region = currentSkin;
        }
        return region;
    }

    public State getState() {
        if(b2body.getLinearVelocity().y > 0)
            return State.UP;
        if(b2body.getLinearVelocity().y < 0)
            return State.DOWN;
        if(b2body.getLinearVelocity().x > 0)
            return State.RIGHT;
        if(b2body.getLinearVelocity().x < 0)
            return State.LEFT;
        else
            return currentState;
    }

    public void defineChef(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / MainGame.PPM,32 / MainGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        chefWidth =  5/MainGame.PPM;
        chefHeight =  10/MainGame.PPM;
        shape.setAsBox(chefWidth, chefHeight);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }











}
