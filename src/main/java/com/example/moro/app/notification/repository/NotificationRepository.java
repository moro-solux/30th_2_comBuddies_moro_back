package com.example.moro.app.notification.repository;

import com.example.moro.app.notification.entity.Notification;
import com.example.moro.app.notification.entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByReceiverIdOrderByCreatedAtDesc(Long receiverId);

    Optional<Notification> findByReceiverIdAndTypeAndTargetId(Long receiverId, NotificationType type, Long targetId);
}
