package com.company.thirdspringbootproject.dto;

public class EmployeeReqDTO {
    private String name;
    private String position;

    public EmployeeReqDTO() {}

    public EmployeeReqDTO(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return this.name;
    }

    public String getPosition() {
        return this.position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof EmployeeReqDTO)) return false;
        final EmployeeReqDTO other = (EmployeeReqDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$position = this.getPosition();
        final Object other$position = other.getPosition();
        if (this$position == null ? other$position != null : !this$position.equals(other$position)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof EmployeeReqDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $position = this.getPosition();
        result = result * PRIME + ($position == null ? 43 : $position.hashCode());
        return result;
    }

    public String toString() {
        return "EmployeeDTO(name=" + this.getName() + ", position=" + this.getPosition() + ")";
    }
}
