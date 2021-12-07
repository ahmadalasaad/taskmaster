package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Task type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Tasks")
@Index(name = "byTeam", fields = {"teamName","title","body","state","file"})
public final class Task implements Model {
  public static final QueryField ID = field("Task", "id");
  public static final QueryField TEAM_NAME = field("Task", "teamName");
  public static final QueryField TITLE = field("Task", "title");
  public static final QueryField BODY = field("Task", "body");
  public static final QueryField STATE = field("Task", "state");
  public static final QueryField FILE = field("Task", "file");
  public static final QueryField LON = field("Task", "lon");
  public static final QueryField LAT = field("Task", "lat");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String teamName;
  private final @ModelField(targetType="String") String title;
  private final @ModelField(targetType="String") String body;
  private final @ModelField(targetType="String") String state;
  private final @ModelField(targetType="String") String file;
  private final @ModelField(targetType="Float") Double lon;
  private final @ModelField(targetType="Float") Double lat;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getTeamName() {
      return teamName;
  }
  
  public String getTitle() {
      return title;
  }
  
  public String getBody() {
      return body;
  }
  
  public String getState() {
      return state;
  }
  
  public String getFile() {
      return file;
  }
  
  public Double getLon() {
      return lon;
  }
  
  public Double getLat() {
      return lat;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Task(String id, String teamName, String title, String body, String state, String file, Double lon, Double lat) {
    this.id = id;
    this.teamName = teamName;
    this.title = title;
    this.body = body;
    this.state = state;
    this.file = file;
    this.lon = lon;
    this.lat = lat;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Task task = (Task) obj;
      return ObjectsCompat.equals(getId(), task.getId()) &&
              ObjectsCompat.equals(getTeamName(), task.getTeamName()) &&
              ObjectsCompat.equals(getTitle(), task.getTitle()) &&
              ObjectsCompat.equals(getBody(), task.getBody()) &&
              ObjectsCompat.equals(getState(), task.getState()) &&
              ObjectsCompat.equals(getFile(), task.getFile()) &&
              ObjectsCompat.equals(getLon(), task.getLon()) &&
              ObjectsCompat.equals(getLat(), task.getLat()) &&
              ObjectsCompat.equals(getCreatedAt(), task.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), task.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTeamName())
      .append(getTitle())
      .append(getBody())
      .append(getState())
      .append(getFile())
      .append(getLon())
      .append(getLat())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Task {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("teamName=" + String.valueOf(getTeamName()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("body=" + String.valueOf(getBody()) + ", ")
      .append("state=" + String.valueOf(getState()) + ", ")
      .append("file=" + String.valueOf(getFile()) + ", ")
      .append("lon=" + String.valueOf(getLon()) + ", ")
      .append("lat=" + String.valueOf(getLat()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TeamNameStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Task justId(String id) {
    return new Task(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      teamName,
      title,
      body,
      state,
      file,
      lon,
      lat);
  }
  public interface TeamNameStep {
    BuildStep teamName(String teamName);
  }
  

  public interface BuildStep {
    Task build();
    BuildStep id(String id);
    BuildStep title(String title);
    BuildStep body(String body);
    BuildStep state(String state);
    BuildStep file(String file);
    BuildStep lon(Double lon);
    BuildStep lat(Double lat);
  }
  

  public static class Builder implements TeamNameStep, BuildStep {
    private String id;
    private String teamName;
    private String title;
    private String body;
    private String state;
    private String file;
    private Double lon;
    private Double lat;
    @Override
     public Task build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Task(
          id,
          teamName,
          title,
          body,
          state,
          file,
          lon,
          lat);
    }
    
    @Override
     public BuildStep teamName(String teamName) {
        Objects.requireNonNull(teamName);
        this.teamName = teamName;
        return this;
    }
    
    @Override
     public BuildStep title(String title) {
        this.title = title;
        return this;
    }
    
    @Override
     public BuildStep body(String body) {
        this.body = body;
        return this;
    }
    
    @Override
     public BuildStep state(String state) {
        this.state = state;
        return this;
    }
    
    @Override
     public BuildStep file(String file) {
        this.file = file;
        return this;
    }
    
    @Override
     public BuildStep lon(Double lon) {
        this.lon = lon;
        return this;
    }
    
    @Override
     public BuildStep lat(Double lat) {
        this.lat = lat;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String teamName, String title, String body, String state, String file, Double lon, Double lat) {
      super.id(id);
      super.teamName(teamName)
        .title(title)
        .body(body)
        .state(state)
        .file(file)
        .lon(lon)
        .lat(lat);
    }
    
    @Override
     public CopyOfBuilder teamName(String teamName) {
      return (CopyOfBuilder) super.teamName(teamName);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder body(String body) {
      return (CopyOfBuilder) super.body(body);
    }
    
    @Override
     public CopyOfBuilder state(String state) {
      return (CopyOfBuilder) super.state(state);
    }
    
    @Override
     public CopyOfBuilder file(String file) {
      return (CopyOfBuilder) super.file(file);
    }
    
    @Override
     public CopyOfBuilder lon(Double lon) {
      return (CopyOfBuilder) super.lon(lon);
    }
    
    @Override
     public CopyOfBuilder lat(Double lat) {
      return (CopyOfBuilder) super.lat(lat);
    }
  }
  
}
