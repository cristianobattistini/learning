package it.cristiano.learning.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "confirmation_tokens")
public class ConfirmationToken {

    @Id
    @SequenceGenerator(
        name="confirmation_token_sequence",
        sequenceName = "confirmation_token_sequence",
        allocationSize = 1
    )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "confirmation_token_sequence")
    private Long idConfirmationToken;

    @Column(nullable=false)
    private String token;

	@Column(nullable=false)
    private LocalDateTime createdAt;

	@Column(nullable=false)
    private LocalDateTime expiredAt;

    private LocalDateTime confirmedAt;

	@ManyToOne
	@JoinColumn(
		name = "id_user",
		nullable = false
	)
    private User user;
    
    public ConfirmationToken() {
    	
    }
    
	public ConfirmationToken(Long idConfirmationToken, String token, LocalDateTime createdAt, LocalDateTime expiredAt,
			LocalDateTime confirmedAt, User user) {
		super();
		this.idConfirmationToken = idConfirmationToken;
		this.token = token;
		this.createdAt = createdAt;
		this.expiredAt = expiredAt;
		this.confirmedAt = confirmedAt;
		this.user = user;
	}

	public Long getIdConfirmationToken() {
		return idConfirmationToken;
	}
	
	public void setIdConfirmationToken(Long idConfirmationToken) {
		this.idConfirmationToken = idConfirmationToken;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public LocalDateTime getExpiredAt() {
		return expiredAt;
	}
	
	public void setExpiredAt(LocalDateTime expiredAt) {
		this.expiredAt = expiredAt;
	}
	
	public LocalDateTime getConfirmedAt() {
		return confirmedAt;
	}
	
	public void setConfirmedAt(LocalDateTime confirmedAt) {
		this.confirmedAt = confirmedAt;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
    
    


    
}
