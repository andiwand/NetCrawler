package at.andiwand.library.math;


public class VectorUtil {
	
	public static Vector2d normalComponent(Vector2d direction, Vector2d vector) {
		return vector.sub(radialComponent(direction, vector));
	}
	public static Vector2d radialComponent(Vector2d direction, Vector2d vector) {
		Vector2d directionNorm = direction.normalize();
		return directionNorm.mul(directionNorm.dot(vector));
	}
	
}