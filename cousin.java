class Member {
    private String name;
    private String gender;
    private Member dad;
    private Member mother;
    private Member[] sister;
    private Member[] brother;

    // Constructor
    public Member(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Member getDad() { return dad; }
    public void setDad(Member dad) { this.dad = dad; }

    public Member getMother() { return mother; }
    public void setMother(Member mother) { this.mother = mother; }

    public Member[] getSister() { return sister; }
    public void setSister(Member[] sister) { this.sister = sister; }

    public Member[] getBrother() { return brother; }
    public void setBrother(Member[] brother) { this.brother = brother; }

    @Override
    public String toString() {
        return "Member{name='" + name + "', gender='" + gender + "'}";
    }
}
class ParentRelationUtility {
    private Member[] members;
    private int count;

    // Constructor initializing the members array
    public ParentRelationUtility(int size) {
        members = new Member[size];
        count = 0;
    }

    // Create or get a member
    private Member createOrGetMember(String name, String gender) {
        for (int i = 0; i < count; i++) {
            if (members[i].getName().equals(name)) {
                return members[i]; // Return existing member
            }
        }
        Member member = new Member(name, gender); // Create new member
        members[count++] = member; // Add to the array
        return member;
    }

    // Load family data
    public void loadFamily(String[][] familyArray) {
        for (String[] memberData : familyArray) {
            // Get or create each family member
            Member member = createOrGetMember(memberData[0], memberData[1]);
            Member dad = createOrGetMember(memberData[2], "Male");
            Member mother = createOrGetMember(memberData[3], "Female");

            // Set family relations
            member.setDad(dad);
            member.setMother(mother);
        }

        // After members are loaded, assign siblings
        addSiblings(familyArray);
    }

    // Assign siblings based on the family array
    private void addSiblings(String[][] familyArray) {
        for (int i = 0; i < count; i++) {
            Member member = members[i];
            Member dad = member.getDad();

            // Get all siblings with the same dad
            Member[] siblings = getSiblings(dad);

            // Divide brothers and sisters
            assignSiblings(member, siblings);
        }
    }

    // Get siblings for the given dad
    private Member[] getSiblings(Member dad) {
        Member[] siblings = new Member[count];
        int siblingCount = 0;
        
        // Collect all members with the same dad
        for (int i = 0; i < count; i++) {
            if (members[i].getDad() != null && members[i].getDad().equals(dad)) {
                siblings[siblingCount++] = members[i];
            }
        }

        // Trim the array to the correct size
        Member[] result = new Member[siblingCount];
        System.arraycopy(siblings, 0, result, 0, siblingCount);
        return result;
    }

    // Assign brothers and sisters based on the gender
    private void assignSiblings(Member member, Member[] siblings) {
        Member[] brothers = new Member[siblings.length];
        Member[] sisters = new Member[siblings.length];
        int brotherCount = 0;
        int sisterCount = 0;

        for (Member sibling : siblings) {
            if (!sibling.equals(member)) { // Exclude the current member
                if (sibling.getGender().equals("Male")) {
                    brothers[brotherCount++] = sibling;
                } else {
                    sisters[sisterCount++] = sibling;
                }
            }
        }

        // Trim arrays to correct size
        Member[] actualBrothers = new Member[brotherCount];
        Member[] actualSisters = new Member[sisterCount];
        System.arraycopy(brothers, 0, actualBrothers, 0, brotherCount);
        System.arraycopy(sisters, 0, actualSisters, 0, sisterCount);

        member.setBrother(actualBrothers);
        member.setSister(actualSisters);
    }

    // Get paternal and maternal cousins
    public Member[] getCousins(String name) {
        Member member = getMemberByName(name);

        if (member == null) {
            return new Member[0]; // No cousins if the member doesn't exist
        }

        Member[] paternalCousins = getCousinsFromParent(member.getDad());
        Member[] maternalCousins = getCousinsFromParent(member.getMother());

        // Combine both paternal and maternal cousins
        Member[] allCousins = new Member[paternalCousins.length + maternalCousins.length];
        System.arraycopy(paternalCousins, 0, allCousins, 0, paternalCousins.length);
        System.arraycopy(maternalCousins, 0, allCousins, paternalCousins.length, maternalCousins.length);

        return allCousins;
    }

    // Get cousins from the parent side (dad or mother)
    private Member[] getCousinsFromParent(Member parent) {
        if (parent == null) {
            return new Member[0];
        }

        Member[] siblings = getSiblings(parent); // Parent's siblings (aunts and uncles)
        Member[] cousins = new Member[count];
        int cousinCount = 0;

        for (Member sibling : siblings) {
            for (Member siblingChild : getSiblings(sibling)) {
                cousins[cousinCount++] = siblingChild;
            }
        }

        // Trim the array to the correct size
        Member[] result = new Member[cousinCount];
        System.arraycopy(cousins, 0, result, 0, cousinCount);
        return result;
    }

    // Helper to get a member by name
    private Member getMemberByName(String name) {
        for (int i = 0; i < count; i++) {
            if (members[i].getName().equals(name)) {
                return members[i];
            }
        }
        return null;
    }
}

