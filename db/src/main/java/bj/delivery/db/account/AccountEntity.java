package bj.delivery.db.account;

import bj.delivery.db.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuperBuilder // 부모의 객체까지 builder() 됨
@Data
@EqualsAndHashCode(callSuper = true) // 부모의 값까지 비교
@Entity
@Table(name = "account")
@RequiredArgsConstructor
public class AccountEntity extends BaseEntity {
}
