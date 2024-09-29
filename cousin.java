
suvetha s
10:00 AM (2 hours ago)
to me

import java.util.Arrays;

class Member {
    String name;
    String gender;
    Member dad;
    Member mother;
    Member[] sister;
    Member[] brother;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Member getDad() {
        return dad;
    }

    public void setDad(Member dad) {
        this.dad = dad;
    }

    public Member getMother() {
        return mother;
    }

    public void setMother(Member mother) {
        this.mother = mother;
    }

    public Member[] getSister() {
        return sister;
    }

    public void setSister(Member[] sister) {
        this.sister = sister;
    }

    public Member[] getBrother() {
        return brother;
    }

    public void setBrother(Member[] brother) {
        this.brother = brother;
    }

    @Override
    public String toString() {
        return "Member [name=" + name + ", gender=" + gender + ", dad=" + dad + ", mother=" + mother + "]";
    }

}

class ParentRelationUtility {
    static Member[] members;

    static int count;

    public static void loadFamily(String[][] familArray) {
        count = 0;
        members = new Member[familArray.length + (2 * familArray.length)];

        for (String[] member : familArray) {

            addToMembers(member);

        }
        addSiblings(familArray);
        // printFamily();
    }

    private static void addSiblings(String[][] familArray) {
        for (String[] member : familArray) {
            Member currentMember = getMemberInstace(member[0]);
            Member[][] sibMembers = getSiblings(currentMember);
            currentMember.setBrother(sibMembers[0]);
            currentMember.setSister(sibMembers[1]);

        }
    }

    private static Member[][] getSiblings(Member currentMember) {
        Member[][] sibMember = new Member[2][count];
        int brotherIndex = 0;
        int sisterIndex = 0;
        Member currentDad = currentMember.getDad();
        for (int index = 0; index < count; index++) {
            Member nextPerson = members[index];
            Member nextPersonDad = nextPerson.getDad();
            if (currentDad == null) {
                return sibMember;
            }
            if (nextPersonDad != null && !currentMember.getName().equals(nextPerson.getName())
                    && nextPersonDad.getName().equals(currentDad.getName())) {
                if (nextPerson.getGender().equals("m")) {
                    sibMember[0][brotherIndex++] = nextPerson;
                } else {
                    sibMember[1][sisterIndex++] = nextPerson;
                }
            }

        }
        return sibMember;
    }

    private static void printFamily() {

        for (int index = 0; index < count; index++) {

            System.out.println(members[index].toString());
        }
    }

    private static void addToMembers(String[] member) {
        Member currentMember = new Member();
        members[count++] = currentMember;
        currentMember.setName(member[0]);
        currentMember.setGender(member[1]);
        currentMember.setDad(createMember(member[2], "m"));
        currentMember.setMother(createMember(member[3], "f"));

    }

    private static Member createMember(String name, String gender) {
        if (name == null)
            return null;
        Member member = getMemberInstace(name);

        member.setGender(gender);

        return member;
    }

    private static Member getMemberInstace(String name) {
        for (int index = 0; index < count; index++) {
            if (members[index].getName().equals(name)) {
                return members[index];
            }

        }
        members[count++] = new Member();
        members[count - 1].setName(name);
        return members[count - 1];

    }

    public static void getCousin(String name) {
        Member member = getMemberInstace(name);
        Member memberDad = member.getDad();
        Member memberMother = member.getMother();
        if (memberMother.getMother() != null) {
            System.out.println("from dad side");
            for (Member dadSiter : memberDad.getSister()) {
                getChild(dadSiter, member);
            }

            if (memberMother.getMother() != null) {
                System.out.println("from mother side");
                for (Member motherBrother : memberMother.getBrother()) {
                    getChild(motherBrother, member);
                }
            }
        }
    }

    private static void getChild(Member person, Member member) {
        if (person != null) {
            if (member.getGender().equals("m")) {
                getDauter(person);
            } else {
                getSon(person);
            }
        }
    }

    private static void getSon(Member member) {
        for (int index = 0; index < count; index++) {
            if (member.getGender().equals("m") && members[index].getDad().getName().equals(member.getName())
                    && members[index].getGender().equals("m")) {
                System.out.println(member.getName());
            } else if (members[index].getMother().getName().equals(member.getName())
                    && members[index].getGender().equals("m")) {
                System.out.println(member.getName());
            }
        }
    }

    private static void getDauter(Member member) {
        for (int index = 0; index < count; index++) {
            if (members[index].getDad() != null) {
                if (member.getGender().equals("m") && members[index].getDad().getName().equals(member.getName())
                        && members[index].getGender().equals("f")) {
                    System.out.println(member.getName());
                } else if (members[index].getMother().getName().equals(member.getName())
                        && members[index].getGender().equals("f")) {
                    System.out.println(member.getName());
                }
            }
        }
    }

}

public class ParentRelation {
    public static void main(String[] args) {
        String[][] familArray = { { "a", "m", null, null }, { "b", "m", "a", "c" }, { "d", "m", "b", "e" },
                { "f", "f", "a", "c" }, { "g", "f", "h", "f" } };
        ParentRelationUtility.loadFamily(familArray);
        ParentRelationUtility.getCousin("d");
    }

}
