package at.mfpjn.workflow.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "state_translation")
public class StateTranslation implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Language")
    private String language;

    @Column(name = "IsDefault")
    private boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "STATE_FK")
    State state_fk;

    public StateTranslation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public State getState_fk() {
        return state_fk;
    }

    public void setState_fk(State state_fk) {
        this.state_fk = state_fk;
    }

    @Override
    public String toString() {
        return "StateTranslation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", isDefault=" + isDefault +
                ", state_fk=" + state_fk +
                '}';
    }
}
