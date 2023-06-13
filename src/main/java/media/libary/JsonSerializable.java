package media.libary;

public interface JsonSerializable {
  /**
   * Serializes the current instance into a JSON representation.
   * @return The JSON representation of the object.
   */
  String toJson();
}
