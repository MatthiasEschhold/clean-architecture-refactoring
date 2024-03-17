package de.arkem.clean.arc.demo.next.level.garageportal.vehicle.domain.model;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.LicensePlate;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LicensePlateTest {

    private final static List<String> AUSTRIA_LICENSE_PLATE_LIST = Arrays.asList(
            "A 1 B",
            "X 12 Y",
            "Zz 123 Ab",
            "K 234 Lm",
            "Mn 345 Op",
            "Pq 456 Rst",
            "Uv 567 Wxy",
            "Zz 678 Xyz",
            "Aa 789 Bcd",
            "Ef 890 Ghi"
    );
    private final static List<String> GERMAN_LICENSE_PLATE_LIST = Arrays.asList(
            "EES-EL 0815",
            "EES-EL 815",
            "EES-EL 15",
            "EES-EL 5",
            "EES-L 0815",
            "EES-L 085",
            "EES-L 08",
            "EES-L 5",
            "ES-EL 0815",
            "ES-EL 085",
            "ES-EL 08",
            "ES-EL 0",
            "ES-L 0815",
            "ES-L 085",
            "ES-L 08",
            "ES-L 5",
            "E-EL 0815",
            "E-EL 085",
            "E-EL 08",
            "E-EL 0",
            "E-L 0815",
            "E-L 085",
            "E-L 08",
            "E-L 5");

    @Test
    public void shouldCreateValidEuLicensePlateValueObjects() {
        List<String> inputList = new ArrayList<>();
        inputList.addAll(GERMAN_LICENSE_PLATE_LIST);
        inputList.addAll(AUSTRIA_LICENSE_PLATE_LIST);
        List<LicensePlate> licensePlateList = createLicensePlateValueObjects(inputList);
        assertThat(licensePlateList, everyItem(hasValueInList(inputList)));
    }

    @Test
    public void shouldThrowExceptionForInvalidEuLicensePlateValueObjects() {
        List<String> inputList = new ArrayList<>();
        inputList.addAll(Arrays.asList("123456", "A 1 B C", "EES-EL 0815 123"));
        for (String input : inputList) {
            assertThrows(IllegalArgumentException.class, () -> new LicensePlate(input));
        }
    }

    /**
     * Hamcrest could not handle records, due to this hasProperty("value", inList(stringList()) does not work
     *
     * @param stringList
     * @return
     */
    private Matcher<LicensePlate> hasValueInList(List<String> stringList) {
        return new TypeSafeMatcher<>() {
            @Override
            protected boolean matchesSafely(LicensePlate licensePlate) {
                return stringList.contains(licensePlate.value());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("value should be in the list");
            }
        };
    }

    private List<LicensePlate> createLicensePlateValueObjects(List<String> stringList) {
        return stringList.stream()
                .map(LicensePlate::new)
                .toList();
    }
}
