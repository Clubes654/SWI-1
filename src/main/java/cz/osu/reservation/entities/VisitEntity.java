package cz.osu.reservation.entities;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "visit", schema = "reservation")
public class VisitEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_visit")
    private int idVisit;
    //  @Basic
    // @Column(name = "booking_date")
    // private Timestamp bookingDate;
    @Basic
    @Column(name = "year")
    private int year;

    @Basic
    @Column(name = "month")
    private int month;

    @Basic
    @Column(name = "day")
    private int day;
    @Basic
    @Column(name = "hour")
    private Time hour;

    @Basic
    @Column(name = "isblocked")
    private String isblocked;
    //  @Basic
    //  @Column(name = "reason_code_reason")
    //  private int reasonCodeReason;
    //  @Basic
    //  @Column(name = "person_id_person")
    // private int personIdPerson;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reason_code_reason", referencedColumnName = "code_reason", nullable = false)
    private ReasonEntity reasonByReasonCodeReason;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id_person", referencedColumnName = "id_person", nullable = false)
    private PersonEntity personByPersonIdPerson;

    public int getIdVisit() {
        return idVisit;
    }

    public void setIdVisit(int idVisit) {
        this.idVisit = idVisit;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    /*// public Timestamp getBookingDate() {
        return bookingDate;
    }

   // public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }*/

    public String getIsblocked() {
        return isblocked;
    }

    public void setIsblocked(String isblocked) {
        this.isblocked = isblocked;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }

    // public int getReasonCodeReason() {
    //    return reasonCodeReason;
    // }

    // public void setReasonCodeReason(int reasonCodeReason) {
    //   this.reasonCodeReason = reasonCodeReason;
    // }

    //public int getPersonIdPerson() {
    //  return personIdPerson;
    //}

    //public void setPersonIdPerson(int personIdPerson) {
    //  this.personIdPerson = personIdPerson;
    //}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisitEntity that = (VisitEntity) o;

        if (idVisit != that.idVisit) return false;
        //  if (reasonCodeReason != that.reasonCodeReason) return false;
        // if (personIdPerson != that.personIdPerson) return false;
        // if (bookingDate != null ? !bookingDate.equals(that.bookingDate) : that.bookingDate != null) return false;
        if (isblocked != null ? !isblocked.equals(that.isblocked) : that.isblocked != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVisit;
        //  result = 31 * result + (bookingDate != null ? bookingDate.hashCode() : 0);
        result = 31 * result + (isblocked != null ? isblocked.hashCode() : 0);
        //result = 31 * result + reasonCodeReason;
        //result = 31 * result + personIdPerson;
        return result;
    }

    public ReasonEntity getReasonByReasonCodeReason() {
        return reasonByReasonCodeReason;
    }

    public void setReasonByReasonCodeReason(ReasonEntity reasonByReasonCodeReason) {
        this.reasonByReasonCodeReason = reasonByReasonCodeReason;
    }

    public PersonEntity getPersonByPersonIdPerson() {
        return personByPersonIdPerson;
    }

    public void setPersonByPersonIdPerson(PersonEntity personByPersonIdPerson) {
        this.personByPersonIdPerson = personByPersonIdPerson;
    }
}
