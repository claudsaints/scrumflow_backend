package com.claudsaints.scrumflow.dto.sprint;

import java.time.Instant;

public record CreateSprintDTO(String title, Instant start, Instant end, String goal) {

}
