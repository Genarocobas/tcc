package inatel.br.nfccontrol.data.typeConverter;

import android.arch.persistence.room.TypeConverter;

import java.sql.Timestamp;

/**
 * A Converter for DB to store correctly Timestamp
 * Convert timestamp to long and long to timestamp to handle the db structure and app logic
 * operations
 *
 * @author Guilherme Yabu <guilhermeyabu@inatel.br>
 * @since 19/03/2018.
 */

public class TimestampTypeConverter {

  @TypeConverter
  public Long fromTimestamp(Timestamp value) {
    return value == null ? null : value.getTime();
  }

  @TypeConverter
  public Timestamp LongToTimestamp(Long value) {
    if (value == null) {
      return null;
    } else {
      return new Timestamp(value);
    }
  }
}

