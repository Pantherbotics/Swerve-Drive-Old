import math

class SwerveModule:
    def __init__(self, isReversed):
        self.isReversed = isReversed
        self.steeringAngle = 0.0
        self.steeringMaxSpeed = 1.5*(2*math.pi) #2pi rad /s
        self.steeringSetpoint = 0.0
        self.driveSpeed = 0.0
        self.kP = 0.7

    def update(self, dt = 0.01):
        output = self.kP * self.getModifiedError()
        output = self.clamp(-1.0, 1.0, output)
        self.steeringAngle += output * self.steeringMaxSpeed * dt
        if self.steeringAngle < -math.pi:
            steeringAngle = 2*math.pi + self.steeringAngle
        if self.steeringAngle > math.pi:
            steeringAngle = steeringAngle - 2*math.pi

    def getError(self):
        return self.steeringSetpoint - self.steeringAngle
    
    def getModifiedError(self):
        return self.boundHalfRadians(self.getError())

    def boundHalfRadians(self, radians):
        while radians >= math.pi:
            radians -=2*math.pi
        while radians < -math.pi: 
            radians +=2*math.pi
        return radians

    def clamp(self, min, max, val):
        if val < min:
            return min
        if val > max:
            return max
        else:
            return val

module = SwerveModule(False)
module.steeringSetpoint = math.pi+0.2

count = 0
while abs(module.getModifiedError()) > 0.0001:
    position = module.steeringAngle
    print(position)
    print(module.getModifiedError())
    module.update()
    count+=1

print(count * 0.01, "seconds elapsed")
