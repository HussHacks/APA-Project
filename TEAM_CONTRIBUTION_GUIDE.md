# FitTrackPro - Team Contribution Guide

## 📋 PROJECT DIVISION INTO 4 EQUAL PARTS

The FitTrackPro project has been divided into 4 logical modules. Each team member is responsible for one complete feature module.

---

## 👥 TEAM MEMBER ASSIGNMENTS

### **Team Member 1: User Management & Authentication**
**Files to work with:**
- `src/main/java/com/fittrack/fittrackpro/entity/User.java` - User data model
- `src/main/java/com/fittrack/fittrackpro/controller/AuthController.java` - Login/Register endpoints
- `src/main/java/com/fittrack/fittrackpro/service/UserService.java` - User business logic
- `src/main/java/com/fittrack/fittrackpro/repository/UserRepository.java` - Database access

**Responsibilities:**
- User registration with validation
- User login/logout functionality
- User profile management
- Password handling
- Email validation

**Key Features:**
- POST /api/auth/register - Register new user
- POST /api/auth/login - User login
- GET /api/users - List all users
- GET /api/users/role/{role} - Filter users by role

---

### **Team Member 2: Meal Tracking Feature**
**Files to work with:**
- `src/main/java/com/fittrack/fittrackpro/entity/Meal.java` - Meal data model
- `src/main/java/com/fittrack/fittrackpro/controller/MealController.java` - Meal endpoints
- `src/main/java/com/fittrack/fittrackpro/service/MealService.java` - Meal business logic
- `src/main/java/com/fittrack/fittrackpro/repository/MealRepository.java` - Database access
- `src/main/java/com/fittrack/fittrackpro/dto/MealSummaryDTO.java` - Data transfer object

**Responsibilities:**
- Create new meal entries
- Track nutritional information (calories, protein, carbs, fats)
- Retrieve user meals
- Calculate daily nutrition summaries
- Delete meal entries

**Key Features:**
- POST /api/meals/{userId} - Add meal
- GET /api/meals/{userId} - Get user meals
- GET /api/meals/summary/{userId}/{date} - Daily nutrition summary
- Reset meals functionality

---

### **Team Member 3: Workout Tracking Feature**
**Files to work with:**
- `src/main/java/com/fittrack/fittrackpro/entity/Workout.java` - Workout data model
- `src/main/java/com/fittrack/fittrackpro/controller/WorkoutController.java` - Workout endpoints
- `src/main/java/com/fittrack/fittrackpro/service/WorkoutService.java` - Workout business logic
- `src/main/java/com/fittrack/fittrackpro/repository/WorkoutRepository.java` - Database access

**Responsibilities:**
- Create workout entries
- Track sets, reps, and weight
- Retrieve user workouts
- Calculate total volume
- Delete workout entries

**Key Features:**
- POST /api/workouts/{userId} - Add workout
- GET /api/workouts/{userId} - Get user workouts
- Reset workouts functionality
- Volume calculations

---

### **Team Member 4: History Tracking & Frontend**
**Files to work with:**
- `src/main/java/com/fittrack/fittrackpro/entity/AuditLog.java` - Audit log data model
- `src/main/java/com/fittrack/fittrackpro/entity/OperationType.java` - Operation types enum
- `src/main/java/com/fittrack/fittrackpro/controller/HistoryController.java` - History endpoints
- `src/main/java/com/fittrack/fittrackpro/controller/DashboardController.java` - Dashboard views
- `src/main/java/com/fittrack/fittrackpro/service/AuditLogService.java` - Audit business logic
- `src/main/java/com/fittrack/fittrackpro/repository/AuditLogRepository.java` - Database access
- `src/main/resources/static/frontend/` - Frontend files (HTML, CSS, JavaScript)
- `src/main/resources/templates/` - Thymeleaf templates

**Responsibilities:**
- Operation history tracking
- Audit logging for compliance
- Dashboard display
- User interface and styling
- Frontend interactions

**Key Features:**
- GET /api/history/user/{userId} - Personal history
- GET /api/history/all - System audit logs
- Dashboard views with role-based routing
- Frontend HTML/CSS/JavaScript

---

## 🛠️ SHARED FILES (All Team Members)

These files are used by everyone and should be coordinated:

- `pom.xml` - Maven dependencies (discuss before changing)
- `src/main/resources/application.properties` - Database config
- `DATABASE_SCHEMA.sql` - Database setup
- `PROJECT_DOCUMENTATION.md` - Master documentation
- `.gitignore` - Git settings
- `FittrackproApplication.java` - Main application entry point
- `Role.java` - Role enum (ADMIN/USER)

---

## 📖 BEGINNER'S GUIDE TO GITHUB & GIT

### **Step 1: Install Git**
Download and install Git from: https://git-scm.com/download/win

### **Step 2: Configure Git Locally**
Open PowerShell and run:
```bash
git config --global user.name "Your Full Name"
git config --global user.email "your.email@example.com"
```

### **Step 3: Clone the Repository (First Time Only)**
Each team member should do this ONCE:
```bash
git clone <repository-url>
cd fittrackpro
```

Replace `<repository-url>` with the URL from your GitHub repository (ends with `.git`)

### **Step 4: Create Your Own Branch**
Each team member creates their own branch:
```bash
git branch "TeamMember1-UserManagement"
git checkout "TeamMember1-UserManagement"
```

**Example branch names:**
- `TeamMember1-UserManagement`
- `TeamMember2-MealTracking`
- `TeamMember3-WorkoutTracking`
- `TeamMember4-History-Frontend`

### **Step 5: Make Your Changes**
Edit your assigned files in VS Code. The files are already created and ready.

### **Step 6: Check What Changed**
After making changes:
```bash
git status
```
This shows which files you modified.

### **Step 7: Stage Your Changes**
Add your changes to the staging area:
```bash
git add .
```

Or add specific files:
```bash
git add src/main/java/com/fittrack/fittrackpro/entity/User.java
git add src/main/java/com/fittrack/fittrackpro/controller/AuthController.java
```

### **Step 8: Create a Commit**
Save your changes with a message:
```bash
git commit -m "Add user registration and login functionality"
```

**Good commit message examples:**
- `Add meal CRUD operations`
- `Implement workout tracking endpoints`
- `Create audit logging system`
- `Design user dashboard`

### **Step 9: Push to GitHub**
Send your changes to the repository:
```bash
git push origin "TeamMember1-UserManagement"
```

Replace `"TeamMember1-UserManagement"` with your branch name.

### **Step 10: Create Pull Request**
1. Go to your GitHub repository URL
2. You'll see a button "Compare & pull request"
3. Click it
4. Add a description of your changes
5. Click "Create pull request"
6. Your team lead or lecturer will review and merge

---

## 📝 WORKFLOW - STEP BY STEP

### **FIRST TIME (Initial Setup)**
```bash
# 1. Clone repository
git clone <repository-url>
cd fittrackpro

# 2. Create your branch
git branch "YourName-YourFeature"
git checkout "YourName-YourFeature"

# 3. Verify your files exist
ls src/main/java/com/fittrack/fittrackpro/entity/
ls src/main/java/com/fittrack/fittrackpro/controller/
```

### **EVERY TIME YOU MAKE CHANGES**
```bash
# 1. Make changes to your files in VS Code

# 2. Check what changed
git status

# 3. Stage your changes
git add .

# 4. Commit with a message
git commit -m "Description of what you added"

# 5. Push to GitHub
git push origin "YourBranchName"

# 6. Go to GitHub and create Pull Request (PR)
```

### **IF SOMEONE ELSE PUSHED CHANGES**
To get the latest code from the main branch:
```bash
git fetch
git pull origin main
```

---

## ⚠️ IMPORTANT RULES

### **DO:**
✅ Push only files you are responsible for  
✅ Commit frequently with clear messages  
✅ Pull before you start working  
✅ Test your code before pushing  
✅ Communicate with team about changes  

### **DON'T:**
❌ Push to main branch directly (always use your branch)  
❌ Delete other people's files  
❌ Change shared files without discussing  
❌ Push untested code  
❌ Make commits with message like "fix" or "update"  

---

## 🆘 COMMON GIT COMMANDS

### **Check your current branch**
```bash
git branch
```

### **See all commits**
```bash
git log
```

### **Undo last commit (if not pushed)**
```bash
git reset --soft HEAD~1
```

### **Undo changes to a file**
```bash
git checkout -- filename.java
```

### **Update your branch with latest main**
```bash
git fetch
git rebase origin/main
```

### **Check differences**
```bash
git diff
```

---

## 📊 TEAM CONTRIBUTION CHECKLIST

### **Team Member 1: User Management**
- [ ] Clone repository
- [ ] Create branch `TeamMember1-UserManagement`
- [ ] Understand User.java structure
- [ ] Review AuthController endpoints
- [ ] Study UserService logic
- [ ] Make your modifications
- [ ] Commit changes with clear messages
- [ ] Push to GitHub
- [ ] Create Pull Request
- [ ] Resolve any merge conflicts
- [ ] Wait for lecturer approval
- [ ] Celebrate! 🎉

### **Team Member 2: Meal Tracking**
- [ ] Clone repository
- [ ] Create branch `TeamMember2-MealTracking`
- [ ] Understand Meal.java structure
- [ ] Review MealController endpoints
- [ ] Study MealService logic
- [ ] Make your modifications
- [ ] Commit changes with clear messages
- [ ] Push to GitHub
- [ ] Create Pull Request
- [ ] Resolve any merge conflicts
- [ ] Wait for lecturer approval
- [ ] Celebrate! 🎉

### **Team Member 3: Workout Tracking**
- [ ] Clone repository
- [ ] Create branch `TeamMember3-WorkoutTracking`
- [ ] Understand Workout.java structure
- [ ] Review WorkoutController endpoints
- [ ] Study WorkoutService logic
- [ ] Make your modifications
- [ ] Commit changes with clear messages
- [ ] Push to GitHub
- [ ] Create Pull Request
- [ ] Resolve any merge conflicts
- [ ] Wait for lecturer approval
- [ ] Celebrate! 🎉

### **Team Member 4: History & Frontend**
- [ ] Clone repository
- [ ] Create branch `TeamMember4-History-Frontend`
- [ ] Understand AuditLog.java structure
- [ ] Review HistoryController endpoints
- [ ] Study AuditLogService logic
- [ ] Review frontend HTML/CSS/JavaScript
- [ ] Make your modifications
- [ ] Commit changes with clear messages
- [ ] Push to GitHub
- [ ] Create Pull Request
- [ ] Resolve any merge conflicts
- [ ] Wait for lecturer approval
- [ ] Celebrate! 🎉

---

## 🎯 WHAT EACH PERSON SHOULD PUSH

### **Person 1 - User Management**
```
src/main/java/com/fittrack/fittrackpro/
├── entity/User.java
├── controller/AuthController.java
├── service/UserService.java
└── repository/UserRepository.java
```

### **Person 2 - Meal Tracking**
```
src/main/java/com/fittrack/fittrackpro/
├── entity/Meal.java
├── controller/MealController.java
├── service/MealService.java
├── repository/MealRepository.java
└── dto/MealSummaryDTO.java
```

### **Person 3 - Workout Tracking**
```
src/main/java/com/fittrack/fittrackpro/
├── entity/Workout.java
├── controller/WorkoutController.java
├── service/WorkoutService.java
└── repository/WorkoutRepository.java
```

### **Person 4 - History & Frontend**
```
src/main/java/com/fittrack/fittrackpro/
├── entity/AuditLog.java
├── entity/OperationType.java
├── controller/HistoryController.java
├── controller/DashboardController.java
├── service/AuditLogService.java
└── repository/AuditLogRepository.java

src/main/resources/
├── static/frontend/
│   ├── app.js
│   ├── dashboard.html
│   ├── login.html
│   ├── register.html
│   └── style.css
└── templates/
    └── dashboard.html
```

---

## 📞 NEED HELP?

**Common Issues:**

**Q: "Permission denied" when pushing?**  
A: Make sure the lecturer added you as a collaborator. Check the repository settings.

**Q: "Merge conflict" error?**  
A: Two people edited the same file. Ask your team lead to resolve it.

**Q: "Cannot find repository"?**  
A: Copy the correct repository URL from GitHub and clone again.

**Q: "Your branch is ahead of main"?**  
A: That's normal! You've made commits that main doesn't have yet. That's what Pull Requests are for.

---

## 🚀 FINAL STEPS

1. **Each person clones the repo** - Do this first!
2. **Create your own branch** - Name it clearly
3. **Edit your assigned files** - Only your part
4. **Commit frequently** - Good messages are important
5. **Push to your branch** - Not directly to main
6. **Create Pull Request** - On GitHub website
7. **Wait for review** - Lecturer will check your code
8. **Merge to main** - Once approved

---

**Good luck team! This is a professional way to collaborate on code.** 🎓

